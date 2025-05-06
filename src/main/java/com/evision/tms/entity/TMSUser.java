package com.evision.tms.entity;

import com.evision.tms.UserType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serial;
import java.util.Collection;

@Getter
@Setter
public class TMSUser extends User {

    @Serial
    private static final long serialVersionUID = 5926468583005150707L;
    private int userId;
    private UserType userType;
    private String username;
    private String password;
    private String userRequest;

    public TMSUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}