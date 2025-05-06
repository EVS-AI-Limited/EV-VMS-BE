package com.evision.tms.service;

import com.evision.tms.constants.UserConstants;
import com.evision.tms.entity.UserDetailEntity;
import com.evision.tms.entity.UserInfo;
import com.evision.tms.repository.AuthUserRepo;
import com.evision.tms.utils.HashAlgorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.io.Serial;
import java.io.Serializable;
import java.security.Key;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Component
@Slf4j
@Service
public class TokenService implements Serializable {
    static String name;

    @Autowired
    private AuthUserRepo authUserRepo;

    @Serial
    private static final long serialVersionUID = -2550185165626007488L;

    @Value("${jwt.secret.token.expiration.minutes}")
    private long tokenExpirationMinutes;
    @Value("${jwt.secret}")
    private String secret;

    public Date getExpirationDateFromToken(String token) {
        log.info("Inside Class: TokenService , Method: getExpirationDateFromToken ");
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String getSubjectFromToken(final String token) {
        log.info("Inside Class: TokenService , Method: getSubjectFromToken ");
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        log.info("Inside Class: TokenService , Method: getClaimFromToken ");
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        log.info("Inside Class: TokenService , Method: getAllClaimsFromToken ");
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        log.info("Inside Class: TokenService , Method: isTokenExpired ");
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateToken(final String token, UserDetails userDetails) {
        log.info("Inside Class: TokenService , Method: validateToken ");
        String subject = getSubjectFromToken(token);
        try {
            final JSONParser jsonParser = new JSONParser();
            final org.json.simple.JSONObject jsonObject = (JSONObject) jsonParser.parse(subject);
            String userName = (String) jsonObject.get(UserConstants.USER_NAME_ATTRIBUTE_NAME);
            return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (ParseException e) {
            log.error("Error while parsing {} {}", subject, e);
            return false;
        }
    }

    public JSONObject createJwtSignedHMAC(final String authData) {
        log.info("Inside Class: TokenService , Method: createJwtSignedHMAC ");
        try {
            final JSONParser jsonParser = new JSONParser();
            final org.json.simple.JSONObject jsonObject = (JSONObject) jsonParser.parse(authData);
            String userName = (String) jsonObject.get(UserConstants.USER_NAME_ATTRIBUTE_NAME);
            String passwordVerification = (String) jsonObject.get(UserConstants.USER_PASSWORD_ATTRIBUTE_NAME);

            HashAlgorithm hashAlgorithm = new HashAlgorithm();
            String password = hashAlgorithm.encryptThisString(passwordVerification).trim();

            if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
                UserInfo userInfo = getUserInfo(userName);

                String name = userInfo.getUserName();
                String newPassword = userInfo.getPassword().trim();
                String userId = String.valueOf(userInfo.getUserId());
                try {
                    boolean nameEquals = name.equals(userName);
                    boolean passEquals = StringUtils.compare(newPassword, password) == 0;
                    if (nameEquals && passEquals) {
                        final Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName());
                        JSONObject jsonObjPayload = new JSONObject();
                        jsonObjPayload.put("userName", name);
                        jsonObjPayload.put("userId", userId);
                        Instant now = Instant.now();
                        String token = Jwts.builder().claim("authDate", jsonObjPayload.toString()).setSubject(jsonObjPayload.toString()).setId(UUID.randomUUID().toString()).setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plus(tokenExpirationMinutes, ChronoUnit.MINUTES))).signWith(hmacKey).compact();

                        JSONObject jsonObj = new JSONObject();
                        jsonObj.put("token", token);
                        jsonObj.put("role", "admin");
                        jsonObj.put("token_expiry", tokenExpirationMinutes);

                        return jsonObj;
                    } else {
                        throw new UsernameNotFoundException(MessageFormat.format("User with userName not found with userName: {0}", userName));
                    }
                } catch (Exception e) {
                    log.error(UserConstants.ENTER_A_VALID_USER_NAME);
                    log.error("Exception in parsing user data", e);
                    throw new UsernameNotFoundException(MessageFormat.format("UserInfo not found with userName: {} Message:{}", name, e.getMessage()));

                }
            } else {
                throw new UsernameNotFoundException(MessageFormat.format("User with userName not found with userName: {0}", userName));
            }

        } catch (ParseException | JsonProcessingException | InterruptedException e) {
            log.error("Exception in parsing user data", e);
            throw new UsernameNotFoundException(MessageFormat.format("UserInfo not found with userName: {} Message:{}", name, e.getMessage()));
        }
    }

    private UserInfo getUserFromDB(String userName) {
        log.info("Inside Class: TokenService , Method: getUserFromDB ");
        UserInfo user = new UserInfo();
        UserDetailEntity userDetailEntity = authUserRepo.findByUserName(userName);
        if (userDetailEntity != null) {
            user.setUserName(userDetailEntity.getUserName());
            user.setPassword(userDetailEntity.getPassword());
            user.setUserId(userDetailEntity.getUserId());
        }
        return user;
    }

    private UserInfo getUserInfo(String userName) throws JsonProcessingException, InterruptedException {
        log.info("Inside Class: TokenService , Method: getUserInfo ");
        if (userName != null) {
            return getUserFromDB(userName);
        }
        return null;
    }

    public void setTokenExpirationMinutes(long tokenExpirationMinutes) {
        log.info("Inside Class: TokenService , Method: setTokenExpirationMinutes ");
        this.tokenExpirationMinutes = tokenExpirationMinutes;
    }
}



