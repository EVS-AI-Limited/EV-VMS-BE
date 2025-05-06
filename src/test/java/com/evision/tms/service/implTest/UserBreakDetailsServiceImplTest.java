package com.evision.tms.service.implTest;

import com.evision.tms.dto.UserBreakDetailsDTO;
import com.evision.tms.entity.TMSConfig;
import com.evision.tms.entity.UserBreakDetails;
import com.evision.tms.repository.TMSConfigRepository;
import com.evision.tms.repository.UserBreakDetailsRepository;
import com.evision.tms.service.impl.UserBreakDetailsServiceImpl;
import com.evision.tms.utils.DateTimeHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserBreakDetailsServiceImplTest {
    @InjectMocks
    private UserBreakDetailsServiceImpl userBreakDetailsService;
    @Mock
    private UserBreakDetailsRepository userBreakDetailsRepository;
    @Spy
    private ObjectMapper objectMapper;
    @Mock
    private UserBreakDetailsDTO userBreakDetailsDTO;
    @Mock
    private UserBreakDetails userBreakDetails;
    @Mock
    private DateTimeHandler dateTimeHandler;
    @Mock
    private TMSConfigRepository tmsConfigRepository;
    private String userBreak;
    private String tmsConfig;
    private String userBreakElseLine;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        userBreakDetailsDTO = new UserBreakDetailsDTO();
        userBreakDetailsDTO.setId(1L);
        userBreakDetailsDTO.setUserBreakId(1);
        userBreakDetailsDTO.setBreakReason("Lunch Break");
        userBreakDetailsDTO.setBreakInOrOut("BreakOut");
        userBreakDetailsDTO.setBreakIn(userBreakDetails.getBreakIn());
        userBreakDetailsDTO.setBreakOut(LocalTime.now().plusHours(5).plusMinutes(30));
        userBreakDetailsDTO.setBreakTime(userBreakDetails.getBreakTime());
        userBreakDetailsDTO.setCreatedDate(LocalDateTime.now().plusHours(5).plusMinutes(30));
        userBreakDetailsDTO.setUpdatedDate(LocalDateTime.now().plusHours(5).plusMinutes(30));


        userBreak = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("user_break_details_api_response.json"),
                StandardCharsets.UTF_8
        );
        tmsConfig = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("tms_config_response.json"),
                StandardCharsets.UTF_8
        );
        userBreakElseLine = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("user_break_details_else_lineResponse.json"),
                StandardCharsets.UTF_8
        );
    }

    @Test
    void getAllBreak() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        List<UserBreakDetails> userBreakDetailsList = objectMapper.readValue(userBreak, new TypeReference<List<UserBreakDetails>>() {
        });
        when(userBreakDetailsRepository.findAll()).thenReturn(userBreakDetailsList);
        List<UserBreakDetailsDTO> userBreakDetailsResponse = userBreakDetailsService.getAllBreak();
        assertEquals(1, userBreakDetailsResponse.get(0).getId());
        assertEquals(1, userBreakDetailsResponse.get(0).getUserBreakId());
        Mockito.when(userBreakDetailsService.getAllBreak()).thenReturn(userBreakDetailsResponse);
    }

    @Test
    void getOneBreak() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        List<UserBreakDetails> userBreakDetailsList = objectMapper.readValue(userBreak, new TypeReference<List<UserBreakDetails>>() {
        });
        when(userBreakDetailsRepository.getReferenceById(userBreakDetailsDTO.getId())).thenReturn(userBreakDetailsList.get(0));
        UserBreakDetailsDTO userBreakDetailsResponse = userBreakDetailsService.getOneBreak(userBreakDetailsDTO.getId());
        assertEquals(1, userBreakDetailsResponse.getId());
        assertEquals(1, userBreakDetailsResponse.getUserBreakId());
    }

    @Test
    void createBreak() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        List<UserBreakDetails> userBreakDetailsList = objectMapper.readValue(userBreak, new TypeReference<List<UserBreakDetails>>() {
        });
        List<TMSConfig> tmsConfigList = objectMapper.readValue(tmsConfig, new TypeReference<List<TMSConfig>>() {
        });
        Mockito.when(tmsConfigRepository.findAll()).thenReturn(tmsConfigList);
        Mockito.when(userBreakDetailsRepository.totalBreakTime(userBreakDetailsDTO.getUserBreakId())).thenReturn(Time.valueOf("01:00:00"));
        Mockito.when(userBreakDetailsRepository.save(userBreakDetailsList.get(0))).thenReturn(userBreakDetailsList.get(0));
        UserBreakDetailsDTO userBreakDetailsResponse = userBreakDetailsService.createBreak(userBreakDetailsDTO.getUserBreakId(), userBreakDetailsDTO);
        assertEquals(1, userBreakDetailsResponse.getUserBreakId());
        assertEquals(1, userBreakDetailsResponse.getId());
    }

    @Test
    void updateBreak() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        List<UserBreakDetails> userBreakDetailsList = objectMapper.readValue(userBreak, new TypeReference<List<UserBreakDetails>>() {
        });
        List<TMSConfig> tmsConfigList = objectMapper.readValue(tmsConfig, new TypeReference<List<TMSConfig>>() {
        });

        LocalTime localTime = LocalTime.parse("01:30:00");
        Mockito.when(dateTimeHandler.getCurrentTime()).thenReturn(localTime);
        Mockito.when(tmsConfigRepository.findAll()).thenReturn(tmsConfigList);
        Mockito.when(userBreakDetailsRepository.getReferenceById(userBreakDetailsDTO.getId())).thenReturn(userBreakDetailsList.get(0));
        Mockito.when(userBreakDetailsRepository.save(userBreakDetailsList.get(0))).thenReturn(userBreakDetailsList.get(0));
        UserBreakDetailsDTO userBreakDetailsResponse = userBreakDetailsService.updateBreak(userBreakDetailsDTO.getId(), userBreakDetailsDTO);
        assertEquals(1, userBreakDetailsResponse.getUserBreakId());
        assertEquals(1, userBreakDetailsResponse.getId());
    }

    @Test
    void updateBreakBreakTimeNotNull() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        List<UserBreakDetails> userBreakDetailsList = objectMapper.readValue(userBreakElseLine, new TypeReference<List<UserBreakDetails>>() {
        });
        List<TMSConfig> tmsConfigList = objectMapper.readValue(tmsConfig, new TypeReference<List<TMSConfig>>() {
        });

        LocalTime localTime = LocalTime.parse("01:30:00");
        Mockito.when(userBreakDetailsRepository.getReferenceById(userBreakDetailsDTO.getId())).thenReturn(userBreakDetailsList.get(0));
        Mockito.when(dateTimeHandler.getCurrentTime()).thenReturn(localTime);
        Mockito.when(tmsConfigRepository.findAll()).thenReturn(tmsConfigList);
        Mockito.when(userBreakDetailsRepository.save(userBreakDetailsList.get(0))).thenReturn(userBreakDetailsList.get(0));
        UserBreakDetailsDTO userBreakDetailsResponse = userBreakDetailsService.updateBreak(userBreakDetailsDTO.getId(), userBreakDetailsDTO);
        assertEquals(1, userBreakDetailsResponse.getUserBreakId());
        assertEquals(1, userBreakDetailsResponse.getId());
    }
}




