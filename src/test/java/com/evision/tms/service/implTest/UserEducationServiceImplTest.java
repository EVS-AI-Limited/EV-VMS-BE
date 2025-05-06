package com.evision.tms.service.implTest;

import com.evision.tms.entity.UserEducationProfile;
import com.evision.tms.dto.UserEducationProfileDTO;
import com.evision.tms.repository.UserEducationRepository;
import com.evision.tms.service.impl.UserEducationServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserEducationServiceImplTest {
    @InjectMocks
    private UserEducationServiceImpl userEducationService;
    @Mock
    private UserEducationRepository userEducationRepository;
    @Mock
    private UserEducationProfileDTO userEducationProfileDTO;
    @Spy
    private ObjectMapper objectMapper;
    private String userEducationDetail;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        userEducationProfileDTO = new UserEducationProfileDTO();
        userEducationProfileDTO.setId(1);
        userEducationProfileDTO.setUserId(1);
        userEducationProfileDTO.setSecondarySchoolName("ABC");
        userEducationProfileDTO.setHighSchoolName("ABD");
        userEducationProfileDTO.setCollegeName("DAVV");
        userEducationProfileDTO.setHighSchoolPassingYear(Year.parse("2021"));
        userEducationProfileDTO.setSecondarySchoolPassingYear(Year.parse("2012"));
        userEducationProfileDTO.setHighSchoolPercentage("");
        userEducationProfileDTO.setSecondarySchoolPercentage("");
        userEducationProfileDTO.setDegreeName("BA");
        userEducationProfileDTO.setFinalCgpa(8.0);


        userEducationDetail = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("user_eduction_profile_response.json"),
                StandardCharsets.UTF_8 );
    }
    @Test
    void getUserEducationProfileList() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        List<UserEducationProfile> userEducationProfileList = objectMapper.readValue(userEducationDetail, new TypeReference<List<UserEducationProfile>>() {
        });
        when(userEducationRepository.findAll()).thenReturn(userEducationProfileList);
        List<UserEducationProfileDTO> userEducationProfileResponse = userEducationService.getUserEducationProfileList();
        assertEquals(1, userEducationProfileResponse.get(0).getId());
        assertEquals(1, userEducationProfileResponse.get(0).getUserId());
    }
    @Test
    void getUserEducationDetailsById() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        List<UserEducationProfile> userEducationProfileList = objectMapper.readValue(userEducationDetail, new TypeReference<List<UserEducationProfile>>() {
        });
        when(userEducationRepository.getReferenceById(userEducationProfileDTO.getUserId())).thenReturn(userEducationProfileList.get(0));
        UserEducationProfileDTO userEducationDetailsById = userEducationService.getUserEducationDetailsById(userEducationProfileDTO.getUserId());
        assertEquals(1,userEducationDetailsById .getId());
        assertEquals(1, userEducationDetailsById.getUserId());
    }
    @Test
    void createUserEducationProfile() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        List<UserEducationProfile> userEducationProfileList = objectMapper.readValue(userEducationDetail, new TypeReference<List<UserEducationProfile>>() {
        });
        Mockito.when(userEducationRepository.save(userEducationProfileList.get(0))).thenReturn(userEducationProfileList.get(0));
        UserEducationProfileDTO userEducationProfile = userEducationService.createUserEducationProfile(userEducationProfileDTO);
        assertEquals(1, userEducationProfile.getId());
        assertEquals(1, userEducationProfile.getUserId());
    }
    @Test
    void updateUserEducationProfile() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        List<UserEducationProfile> userEducationProfileList = objectMapper.readValue(userEducationDetail, new TypeReference<List<UserEducationProfile>>() {
        });
        Mockito.when(userEducationRepository.save(userEducationProfileList.get(0))).thenReturn(userEducationProfileList.get(0));
        UserEducationProfileDTO userEducationProfileDTO1 = userEducationService.updateUserEducationProfile(userEducationProfileDTO, userEducationProfileDTO.getUserId());
        assertEquals(1, userEducationProfileDTO1.getId());
        assertEquals(1, userEducationProfileDTO1.getUserId());
    }
    @Test
    void deleteUserLoginDetails() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        List<UserEducationProfile> userDetailEntities = objectMapper.readValue(userEducationDetail, new TypeReference<List<UserEducationProfile>>() {
        });
        Mockito.when(userEducationRepository.getReferenceById(userEducationProfileDTO.getUserId())).thenReturn(userDetailEntities.get(0));
        userEducationRepository.delete(userDetailEntities.get(0));
        userEducationService.deleteUserEducationDetails(userEducationProfileDTO.getId());
    }
}