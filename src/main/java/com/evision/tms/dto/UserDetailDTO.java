package com.evision.tms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDetailDTO {
    private int userId;
    @NotNull
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    @Email
    private String email;
    private Long mobile;

}
