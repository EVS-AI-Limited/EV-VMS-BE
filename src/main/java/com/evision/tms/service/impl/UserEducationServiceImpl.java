package com.evision.tms.service.impl;

import com.evision.tms.entity.UserEducationProfile;
import com.evision.tms.dto.UserEducationProfileDTO;
import com.evision.tms.repository.UserEducationRepository;
import com.evision.tms.service.UserEducationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserEducationServiceImpl implements UserEducationService {
    @Autowired
    private UserEducationRepository userEducationRepository;

    private List<UserEducationProfileDTO>prepareResponse(List<UserEducationProfile>list){
        log.info("Inside Class: UserEducationServiceImpl , Method: prepareResponse");
        List<UserEducationProfileDTO> responseList = new ArrayList<>();
        for(UserEducationProfile list1 : list){
            UserEducationProfileDTO userEducationProfileDTO = new UserEducationProfileDTO();
            BeanUtils.copyProperties(list1,userEducationProfileDTO);
            responseList.add(userEducationProfileDTO);
        }
        return responseList;
    }

    @Override
    public List<UserEducationProfileDTO> getUserEducationProfileList() {
        log.info("Inside Class: UserEducationServiceImpl , Method: getUserEducationProfileList");
        List<UserEducationProfile> userEducationProfileList = userEducationRepository.findAll();
        return prepareResponse(userEducationProfileList);
    }

    @Override
    public UserEducationProfileDTO getUserEducationDetailsById(int userId) {
        log.info("Inside Class: UserEducationServiceImpl , Method: getUserEducationDetailsById");
        UserEducationProfile userEducationProfile = userEducationRepository.getReferenceById(userId);
        UserEducationProfileDTO userEducationProfileDTO = new UserEducationProfileDTO();
        BeanUtils.copyProperties(userEducationProfile,userEducationProfileDTO);
        return userEducationProfileDTO;
    }

    @Override
    public UserEducationProfileDTO createUserEducationProfile(UserEducationProfileDTO userEducationProfileDTO) {
        log.info("Inside Class: UserEducationServiceImpl , Method: createUserEducationProfile");
        UserEducationProfile userEducationProfile=new UserEducationProfile();
        BeanUtils.copyProperties(userEducationProfileDTO, userEducationProfile);
        userEducationRepository.save(userEducationProfile);
        userEducationProfileDTO.setId(userEducationProfile.getId());
        return userEducationProfileDTO;
    }

    @Override
    public UserEducationProfileDTO updateUserEducationProfile( UserEducationProfileDTO userEducationProfileDTO ,int id) {
        log.info("Inside Class: UserEducationServiceImpl , Method: updateUserEducationProfile");
        userEducationProfileDTO.setId(id);
        UserEducationProfile userEducationProfile=new UserEducationProfile();
        BeanUtils.copyProperties(userEducationProfileDTO, userEducationProfile);
        UserEducationProfile getId = userEducationRepository.getReferenceById(id);
        userEducationRepository.save(getId);
        userEducationRepository.save(userEducationProfile);
        return userEducationProfileDTO;
    }

    @Override
    public void deleteUserEducationDetails(int id) {
        log.info("Inside Class: UserEducationServiceImpl , Method: deleteUserEducationDetails");
        UserEducationProfile userEducationProfile = userEducationRepository.getReferenceById(id);
        this.userEducationRepository.delete(userEducationProfile);
    }
}
