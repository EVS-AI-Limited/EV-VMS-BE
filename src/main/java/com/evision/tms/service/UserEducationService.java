package com.evision.tms.service;

import com.evision.tms.dto.UserEducationProfileDTO;
import java.util.List;

public interface UserEducationService {
    List<UserEducationProfileDTO> getUserEducationProfileList();
    UserEducationProfileDTO getUserEducationDetailsById(int userId);
    UserEducationProfileDTO createUserEducationProfile( UserEducationProfileDTO userEducationProfileDTO);
    UserEducationProfileDTO updateUserEducationProfile(UserEducationProfileDTO userEducationProfileDTO , int id);
    void deleteUserEducationDetails(int id);
}

