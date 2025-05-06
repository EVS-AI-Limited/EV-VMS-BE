package com.evision.tms.entity;

import com.evision.tms.UserType;
import com.evision.tms.dto.UserLoginDetailsDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class UserInfo {
    private int userId;
    private String userName;
    private String password;
    private UserType userType;
    private List<UserLoginDetailsDTO> userLoginDetailsDTOList;
}
