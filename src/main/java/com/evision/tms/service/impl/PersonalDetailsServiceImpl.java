package com.evision.tms.service.impl;

import com.evision.tms.dto.PersonalDetailsDTO;
import com.evision.tms.entity.PersonalDetailsEntity;
import com.evision.tms.repository.PersonalDetailsRepository;
import com.evision.tms.service.PersonalDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PersonalDetailsServiceImpl implements PersonalDetailsService {
    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;

    private List<PersonalDetailsDTO> prepareResponse(List<PersonalDetailsEntity> list) {
        log.info("Inside Class: PersonalDetailsServiceImpl , Method: prepareResponse ");
        List<PersonalDetailsDTO> personalDetailsDTO = new ArrayList<>();
        for (PersonalDetailsEntity personalDetailsEntity : list) {
            PersonalDetailsDTO personalDetailsDTO1 = new PersonalDetailsDTO();
            BeanUtils.copyProperties(personalDetailsEntity, personalDetailsDTO1);
            personalDetailsDTO.add(personalDetailsDTO1);
        }
        return personalDetailsDTO;
    }

    @Override
    public List<PersonalDetailsDTO> getPersonalDetails() {
        log.info("Inside Class: PersonalDetailsServiceImpl , Method: getPersonalDetails ");
        List<PersonalDetailsEntity> personalDetailsEntity = personalDetailsRepository.findAll();
        return prepareResponse(personalDetailsEntity);
    }

    @Override
    public PersonalDetailsDTO createPersonalDetails(PersonalDetailsDTO personalDetailsDTO) {
        log.info("Inside Class: PersonalDetailsServiceImpl , Method: createPersonalDetails ");
        PersonalDetailsEntity personalDetailsEntity = new PersonalDetailsEntity();
        BeanUtils.copyProperties(personalDetailsDTO, personalDetailsEntity);
        personalDetailsDTO.setId(personalDetailsEntity.getId());
        this.personalDetailsRepository.save(personalDetailsEntity);
        personalDetailsDTO.setId(personalDetailsEntity.getId());
        return personalDetailsDTO;
    }

    @Override
    public PersonalDetailsDTO updatePersonalDetails(Long id, PersonalDetailsDTO personalDetailsDTO) {
        log.info("Inside Class: PersonalDetailsServiceImpl , Method: updatePersonalDetails ");
        if (id == personalDetailsDTO.getId()) {
            PersonalDetailsEntity personalDetailsEntity = new PersonalDetailsEntity();
            BeanUtils.copyProperties(personalDetailsDTO, personalDetailsEntity);
            PersonalDetailsEntity getId = personalDetailsRepository.getOne(id);
            PersonalDetailsEntity saveGetId = personalDetailsRepository.save(getId);
            this.personalDetailsRepository.save(personalDetailsEntity);
            return personalDetailsDTO;
        } else {
            throw new RuntimeException("Id Does Not Have Exist In DataBase");
        }
    }

    @Override
    public PersonalDetailsDTO getPersonalDetail(Long id) {
        log.info("Inside Class: PersonalDetailsServiceImpl , Method: getPersonalDetail ");
        PersonalDetailsEntity personalDetailsEntity = personalDetailsRepository.getReferenceById(id);
        PersonalDetailsDTO personalDetailsDTO = new PersonalDetailsDTO();
        BeanUtils.copyProperties(personalDetailsEntity,personalDetailsDTO);
        return personalDetailsDTO;
    }

    @Override
    public void deletePersonalDetail(Long id) {
        log.info("Inside Class: PersonalDetailsServiceImpl , Method: deletePersonalDetail ");
        PersonalDetailsEntity personalDetailsEntity = personalDetailsRepository.getReferenceById(id);
        personalDetailsRepository.delete(personalDetailsEntity);
    }
}