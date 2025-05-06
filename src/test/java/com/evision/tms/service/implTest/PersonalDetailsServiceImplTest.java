package com.evision.tms.service.implTest;

import com.evision.tms.dto.PersonalDetailsDTO;
import com.evision.tms.entity.PersonalDetailsEntity;
import com.evision.tms.repository.PersonalDetailsRepository;
import com.evision.tms.service.impl.PersonalDetailsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PersonalDetailsServiceImplTest {
    @InjectMocks
    private PersonalDetailsServiceImpl personalDetailsService;
    @Mock
    private PersonalDetailsRepository personalDetailsRepository;
    @Spy
    private ObjectMapper objectMapper;

    private PersonalDetailsDTO personalDetailsDTO;

    private String personalDetails;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        personalDetailsDTO = new PersonalDetailsDTO();
        personalDetailsDTO.setId(1L);
        personalDetailsDTO.setPersonalMailId("arnav@gmail.com");
        personalDetailsDTO.setFatherName("Vijay kumar");
        personalDetailsDTO.setMotherName("Sunita kumar");
        personalDetailsDTO.setAadharNumber(8635427635726L);
        personalDetailsDTO.setPanNumber("HBGVG4488J");
        personalDetailsDTO.setPersonalContactNumber("6746376873");
        personalDetailsDTO.setFatherContactNumber("8379375896");
        personalDetailsDTO.setCurrentAddress("201,gandhi nagar");
        personalDetailsDTO.setPermanentAddress("201,gandhi nagar");

        personalDetails =
                IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("personal_details_api_response.json"),
                        StandardCharsets.UTF_8
                );
    }

    @Test
    void getPersonalDetails() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<PersonalDetailsEntity> personalDetailsEntities = objectMapper.readValue(personalDetails, new TypeReference<List<PersonalDetailsEntity>>() {
        });
        when(personalDetailsRepository.findAll()).thenReturn(personalDetailsEntities);
        List<PersonalDetailsDTO> personalDetailsResponse = personalDetailsService.getPersonalDetails();
        assertEquals("chetan@gmail.com", personalDetailsResponse.get(0).getPersonalMailId());
        assertEquals("Vijay", personalDetailsResponse.get(0).getFatherName());
        Mockito.when(personalDetailsService.getPersonalDetails()).thenReturn(personalDetailsResponse);
    }

    @Test
    void createPersonalDetails() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<PersonalDetailsEntity> personalDetailsEntities = objectMapper.readValue(personalDetails, new TypeReference<List<PersonalDetailsEntity>>() {
        });
        Mockito.when(personalDetailsRepository.save(personalDetailsEntities.get(0))).thenReturn(personalDetailsEntities.get(0));
        PersonalDetailsDTO personalDetailsResponse = personalDetailsService.createPersonalDetails(personalDetailsDTO);
        assertEquals("Vijay kumar", personalDetailsResponse.getFatherName());
        assertEquals("arnav@gmail.com", personalDetailsResponse.getPersonalMailId());
    }

    @Test
    void updatePersonalDetails() throws JsonProcessingException,RuntimeException{
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<PersonalDetailsEntity> personalDetailsEntities = objectMapper.readValue(personalDetails, new TypeReference<List<PersonalDetailsEntity>>() {
        });
        Mockito.when(personalDetailsRepository.save(personalDetailsEntities.get(0))).thenReturn(personalDetailsEntities.get(0));
        PersonalDetailsDTO personalDetailsResponse = personalDetailsService.updatePersonalDetails(personalDetailsDTO.getId(), personalDetailsDTO);
        assertEquals("Vijay kumar", personalDetailsResponse.getFatherName());
        assertEquals("arnav@gmail.com", personalDetailsResponse.getPersonalMailId());
    }

    @Test
    void getPersonalDetail() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<PersonalDetailsEntity> personalDetailsEntities = objectMapper.readValue(personalDetails, new TypeReference<List<PersonalDetailsEntity>>() {
        });
        when(personalDetailsRepository.getReferenceById(personalDetailsDTO.getId())).thenReturn(personalDetailsEntities.get(0));
        PersonalDetailsDTO personalDetailsResponse = personalDetailsService.getPersonalDetail(personalDetailsDTO.getId());
        assertEquals("Vijay", personalDetailsResponse.getFatherName());
        assertEquals("chetan@gmail.com", personalDetailsResponse.getPersonalMailId());
    }

    @Test
    void deletePersonalDetail() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<PersonalDetailsEntity> accountDetailEntities = objectMapper.readValue(personalDetails, new TypeReference<List<PersonalDetailsEntity>>() {
        });
        Mockito.when(personalDetailsRepository.getReferenceById(personalDetailsDTO.getId())).thenReturn(accountDetailEntities.get(0));
        personalDetailsRepository.delete(accountDetailEntities.get(0));
        personalDetailsService.deletePersonalDetail(personalDetailsDTO.getId());
    }
}
