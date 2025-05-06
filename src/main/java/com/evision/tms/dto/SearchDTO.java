package com.evision.tms.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SearchDTO {
    @JsonIgnore
    private int userId;
    private String userName;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String password;
    private String email;
    private  Long mobile;
    @JsonIgnore
    private boolean isActive;

}
