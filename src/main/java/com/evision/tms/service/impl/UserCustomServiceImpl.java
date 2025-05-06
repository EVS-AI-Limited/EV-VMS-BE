package com.evision.tms.service.impl;

import com.evision.tms.UserType;
import com.evision.tms.dto.UserLoginDetailsDTO;
import com.evision.tms.entity.UserDetailEntity;
import com.evision.tms.service.UserCustomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCustomServiceImpl implements UserCustomService {
    @Override
    public List<UserLoginDetailsDTO> getAllRolesByUserId(UserDetailEntity userData) {
        log.info("Inside Class: UserCustomServiceImpl , Method: getAllRolesByUserId ");
        return null;
    }

    @Override
    public UserType getUserType(UserDetailEntity userData) {
        log.info("Inside Class: UserCustomServiceImpl , Method: getUserType ");
        return null;
    }
}
