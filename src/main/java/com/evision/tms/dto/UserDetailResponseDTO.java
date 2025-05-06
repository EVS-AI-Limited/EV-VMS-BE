package com.evision.tms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDetailResponseDTO {

    private int userId;
    @NotNull
    private String userName;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @JsonIgnore
    private String password;
    @Email
    private String email;

    private Long mobile;
}
