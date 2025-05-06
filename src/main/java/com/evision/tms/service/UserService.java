package com.evision.tms.service;

import com.evision.tms.dto.UserDetailDTO;
import com.evision.tms.dto.UserDetailRequestDTO;
import com.evision.tms.dto.UserDetailResponseDTO;

import java.util.List;

public interface UserService {

     List<UserDetailResponseDTO> getUserDetails();

     UserDetailResponseDTO getUserDetail(int userId);

     UserDetailResponseDTO registerUser(UserDetailRequestDTO requestDetail);

     public UserDetailResponseDTO updateUserDetail(int userId, UserDetailDTO userDetailDTO);

     void deleteUserDetail(int userId);


}
