package com.evision.tms.service;

import com.evision.tms.UserType;
import com.evision.tms.dto.UserLoginDetailsDTO;
import com.evision.tms.entity.UserDetailEntity;

import java.util.List;

public interface UserCustomService {
    List<UserLoginDetailsDTO> getAllRolesByUserId(UserDetailEntity userData);

    UserType getUserType(UserDetailEntity userData);

}
