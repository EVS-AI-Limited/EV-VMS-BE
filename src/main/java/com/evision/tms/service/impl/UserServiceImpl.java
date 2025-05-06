package com.evision.tms.service.impl;

import com.evision.tms.dto.UserDetailDTO;
import com.evision.tms.dto.UserDetailRequestDTO;
import com.evision.tms.dto.UserDetailResponseDTO;
import com.evision.tms.entity.UserDetailEntity;
import com.evision.tms.repository.UserRepository;
import com.evision.tms.service.UserService;
import com.evision.tms.utils.HashAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private List<UserDetailResponseDTO> prepareResponse(List<UserDetailEntity> list) {
        log.info("Inside Class: UserServiceImpl , Method: prepareResponse ");
        List<UserDetailResponseDTO> userDetailResponseDTOList = new ArrayList<>();
        for (UserDetailEntity userDetail : list) {
            UserDetailResponseDTO userDetailResponseDTO = new UserDetailResponseDTO();
            BeanUtils.copyProperties(userDetail, userDetailResponseDTO);
            userDetailResponseDTOList.add(userDetailResponseDTO);
        }
        return userDetailResponseDTOList;
    }

    @Override
    public List<UserDetailResponseDTO> getUserDetails() {
        log.info("Inside Class: UserServiceImpl , Method: getUserDetails ");
        List<UserDetailEntity> userDetails = userRepository.findAll();
        return prepareResponse(userDetails);
    }


    @Override
    public UserDetailResponseDTO getUserDetail(int userId) {
        log.info("Inside Class: UserServiceImpl , Method: getUserDetails ");
        UserDetailEntity userDetail = userRepository.getReferenceById(userId);
        UserDetailResponseDTO userDetailResponseDTO = new UserDetailResponseDTO();
        BeanUtils.copyProperties(userDetail, userDetailResponseDTO);
        return userDetailResponseDTO;
    }

    @Override
    public UserDetailResponseDTO registerUser(UserDetailRequestDTO requestDetail) {
        log.info("Inside Class: UserServiceImpl , Method: registerUser ");
        HashAlgorithm hashAlgorithm = new HashAlgorithm();
        String password = HashAlgorithm.encryptThisString(requestDetail.getPassword()).trim();
        UserDetailEntity userDetail = new UserDetailEntity();
        BeanUtils.copyProperties(requestDetail, userDetail);
        userDetail.setPassword(password);
        this.userRepository.save(userDetail);
        UserDetailResponseDTO userDetailResponseDTO = new UserDetailResponseDTO();
        userDetailResponseDTO.setUserId(requestDetail.getUserId());
        userDetailResponseDTO.setUserName(requestDetail.getUserName());
        userDetailResponseDTO.setFirstName(requestDetail.getFirstName());
        userDetailResponseDTO.setLastName(requestDetail.getLastName());
        userDetailResponseDTO.setEmail(requestDetail.getEmail());
        userDetailResponseDTO.setMobile(requestDetail.getMobile());
        return userDetailResponseDTO;
    }

    @Override
    public UserDetailResponseDTO updateUserDetail(int userId, UserDetailDTO userDetailDTO) {
        log.info("Inside Class: UserServiceImpl , Method: updateUserDetail ");
        if (userId == userDetailDTO.getUserId()) {
            HashAlgorithm hashAlgorithm = new HashAlgorithm();
            String password = HashAlgorithm.encryptThisString(userDetailDTO.getPassword()).trim();
            UserDetailEntity userDetail = new UserDetailEntity();
            BeanUtils.copyProperties(userDetailDTO, userDetail);
            userDetail.setPassword(password);
            UserDetailEntity one = userRepository.getOne(userId);
            UserDetailEntity save = userRepository.save(one);
            this.userRepository.save(userDetail);
            UserDetailResponseDTO userDetailResponseDTO = new UserDetailResponseDTO();
            userDetailResponseDTO.setUserId(userDetailDTO.getUserId());
            userDetailResponseDTO.setUserName(userDetailDTO.getUserName());
            userDetailResponseDTO.setFirstName(userDetailDTO.getFirstName());
            userDetailResponseDTO.setLastName(userDetailDTO.getLastName());
            userDetailResponseDTO.setEmail(userDetailDTO.getEmail());
            userDetailResponseDTO.setMobile(userDetailDTO.getMobile());
            return userDetailResponseDTO;
        } else {
            throw new RuntimeException("UserId Does Not Have Exist In DataBase");
        }
    }

    @Override
    public void deleteUserDetail(int userId) {
        log.info("Inside Class: UserServiceImpl , Method: deleteUserDetail ");
        UserDetailEntity getId = userRepository.getReferenceById(userId);
        this.userRepository.delete(getId);

    }
}
