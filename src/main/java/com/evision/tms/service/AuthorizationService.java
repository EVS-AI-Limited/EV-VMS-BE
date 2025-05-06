package com.evision.tms.service;

import com.evision.tms.constants.UserConstants;
import com.evision.tms.entity.UserDetailEntity;
import com.evision.tms.entity.TMSUser;
import com.evision.tms.entity.UserInfo;
import com.evision.tms.repository.AuthUserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final AuthUserRepo authUserRepo;

    @Override
    public UserDetails loadUserByUsername(final String userData) throws UsernameNotFoundException {
        log.info("Inside Class: AuthorizationService , Method: loadUserByUsername ");
        final JSONParser jsonParser = new JSONParser();
        Integer userId1 = null;
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(userData);
            String userID= String.valueOf(Integer.parseInt(String.valueOf(jsonObject.get(UserConstants.USER_NAME_ATTRIBUTE_USERID))));
            String userName = String.valueOf(jsonObject.get(UserConstants.USER_NAME_ATTRIBUTE_NAME));
            String password = String.valueOf(jsonObject.get(UserConstants.USER_PASSWORD_ATTRIBUTE_NAME));
            log.info("Requested receive by userName{}", userName);

            if (userName != null && !userName.trim().equals("") && userID != null && !userID.trim().equals("")) {
                UserInfo userInfo = getUserInfo(userName);
                String newUserId= String.valueOf(userInfo.getUserId());
                String name = userInfo.getUserName();
                String newPassword = userInfo.getPassword();

                List<GrantedAuthority> grandAuthorities = getGrantedAuthorities(userInfo);
                TMSUser tmsUser = new TMSUser(userName, "", grandAuthorities);

                tmsUser.setUsername(userName);
                tmsUser.setPassword(password);
                tmsUser.setUserId(Integer.parseInt(userID));


                try {
                    if (name.equals(userName) && newUserId.equals(userID)) {
                        return tmsUser;
                    }
                } catch (NullPointerException e) {
                    System.out.println(UserConstants.ENTER_A_VALID_USER_NAME);
                }
            } else {
                System.out.println(UserConstants.USER_NOT_FOUND);
            }

        } catch (ParseException | JsonProcessingException | InterruptedException e) {
            log.error("Exception in parsing user data", e);
            throw new UsernameNotFoundException(MessageFormat.format("UserInfo not found with userId: {} Message:{}", userId1, e.getMessage()));
        }
        return null;
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserInfo userInfo) {
        log.info("Inside Class: AuthorizationService , Method: getGrantedAuthorities ");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(userInfo.toString()));
        return grantedAuthorities;
    }

    private UserInfo getUserInfo(String userName) throws JsonProcessingException, InterruptedException {
        log.info("Inside Class: AuthorizationService , Method: getUserInfo ");
        if (userName != null) {
            return getUserFromDB(userName);
        }
        return null;
    }

    private UserInfo getUserFromDB(String userName) {
        log.info("Inside Class: AuthorizationService , Method: getUserFromDB ");
        UserInfo user = new UserInfo();
        UserDetailEntity userDetailEntity = authUserRepo.findByUserName(userName);
        if (userDetailEntity != null) {
            user.setUserName(userDetailEntity.getUserName());
            user.setPassword(userDetailEntity.getPassword());
            user.setUserId(userDetailEntity.getUserId());
        }
        return user;
    }
}


