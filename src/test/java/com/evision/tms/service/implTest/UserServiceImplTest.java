package com.evision.tms.service.implTest;


import com.evision.tms.dto.UserDetailDTO;
import com.evision.tms.dto.UserDetailRequestDTO;
import com.evision.tms.dto.UserDetailResponseDTO;
import com.evision.tms.entity.UserDetailEntity;
import com.evision.tms.repository.UserRepository;
import com.evision.tms.service.impl.UserServiceImpl;
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

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Spy
    private ObjectMapper objectMapper;
    @Mock
    private UserDetailDTO userDetailDTO;
    @Mock
    private UserDetailRequestDTO userDetailRequestDTO;
    @Mock
    private UserDetailResponseDTO userDetailResponseDTO;
    private String userDetail;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        userDetailDTO = new UserDetailDTO();
        userDetailDTO.setUserId(1);
        userDetailDTO.setUserName("suraj");
        userDetailDTO.setFirstName("Suraj");
        userDetailDTO.setLastName("Chouhan");
        userDetailDTO.setPassword("12345");
        userDetailDTO.setEmail("suraj@gmail");
        userDetailDTO.setMobile(99L);

        userDetailRequestDTO = new UserDetailRequestDTO();
        userDetailRequestDTO.setUserId(1);
        userDetailRequestDTO.setUserName("suraj");
        userDetailRequestDTO.setFirstName("Suraj");
        userDetailRequestDTO.setLastName("Chouhan");
        userDetailRequestDTO.setPassword("12345");
        userDetailRequestDTO.setEmail("suraj@gmail");
        userDetailRequestDTO.setMobile(99L);

        userDetail = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("user_detail_api_response.json"),
                StandardCharsets.UTF_8
        );
    }

    @Test
    void getUserDetails() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<UserDetailEntity> userDetailEntities = objectMapper.readValue(userDetail, new TypeReference<List<UserDetailEntity>>() {
        });
        when(userRepository.findAll()).thenReturn(userDetailEntities);
        List<UserDetailResponseDTO> userDetailResponseList = userService.getUserDetails();
        assertEquals("ankitlohani", userDetailResponseList.get(0).getUserName());
        assertEquals("Ankit", userDetailResponseList.get(0).getFirstName());

    }

    @Test
    void getUserDetail() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<UserDetailEntity> userDetailEntities = objectMapper.readValue(userDetail, new TypeReference<List<UserDetailEntity>>() {
        });
        when(userRepository.getReferenceById(userDetailDTO.getUserId())).thenReturn(userDetailEntities.get(0));
        UserDetailResponseDTO userDetailResponse = userService.getUserDetail(userDetailDTO.getUserId());
        assertEquals("ankitlohani", userDetailResponse.getUserName());
        assertEquals("Ankit", userDetailResponse.getFirstName());
    }

    @Test
    void registerUser() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<UserDetailEntity> userDetailEntities = objectMapper.readValue(userDetail, new TypeReference<List<UserDetailEntity>>() {
        });
        Mockito.when(userRepository.save(userDetailEntities.get(0))).thenReturn(userDetailEntities.get(0));
        UserDetailResponseDTO userDetailResponse = userService.registerUser(userDetailRequestDTO);
        assertEquals("suraj", userDetailResponse.getUserName());
        assertEquals("Suraj", userDetailResponse.getFirstName());
    }

    @Test
    void updateUserDetail() throws Exception {
        List<UserDetailEntity> userDetailEntities = objectMapper.readValue(userDetail, new TypeReference<List<UserDetailEntity>>() {
        });
        Mockito.when(userRepository.save(userDetailEntities.get(0))).thenReturn(userDetailEntities.get(0));
        UserDetailResponseDTO userDetailResponse = userService.updateUserDetail(userDetailDTO.getUserId(), userDetailDTO);
        assertEquals("suraj", userDetailResponse.getUserName());
        assertEquals("Suraj", userDetailResponse.getFirstName());
    }

    @Test
    void deleteUserDetail() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<UserDetailEntity> userDetailEntities = objectMapper.readValue(userDetail, new TypeReference<List<UserDetailEntity>>() {
        });
        Mockito.when(userRepository.getReferenceById(userDetailDTO.getUserId())).thenReturn(userDetailEntities.get(0));
        userRepository.delete(userDetailEntities.get(0));
        userService.deleteUserDetail(userDetailDTO.getUserId());

    }
}
