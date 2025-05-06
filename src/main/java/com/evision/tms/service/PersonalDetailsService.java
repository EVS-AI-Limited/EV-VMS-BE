package com.evision.tms.service;

import com.evision.tms.dto.PersonalDetailsDTO;

import java.util.List;

public interface PersonalDetailsService {

    List<PersonalDetailsDTO> getPersonalDetails();
    PersonalDetailsDTO createPersonalDetails(PersonalDetailsDTO personalDetailsDTO);
    PersonalDetailsDTO updatePersonalDetails(Long id,PersonalDetailsDTO personalDetailsDTO);
    PersonalDetailsDTO getPersonalDetail(Long id);
    void deletePersonalDetail(Long id);
}
