package com.evision.tms.service.implTest;

import com.evision.tms.dto.SearchDTO;
import com.evision.tms.entity.UserDetailEntity;
import com.evision.tms.repository.SearchRepository;
import com.evision.tms.service.impl.SearchServiceImpl;
import com.evision.tms.utils.SearchBarHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.evision.tms.constants.UserConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SearchServiceImplTest {
    @InjectMocks
    private SearchServiceImpl searchService;
    @Mock
    private SearchRepository searchRepository;
    @Spy
    private ObjectMapper objectMapper;
    @Mock
    private SearchDTO searchDTO;
    @Mock
    private SearchBarHandler searchBarHandler;
    private String userDetail;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        searchDTO = new SearchDTO();
        searchDTO.setUserId(1);
        searchDTO.setUserName("kishan");
        searchDTO.setFirstName("Kishan");
        searchDTO.setLastName("kumar");
        searchDTO.setEmail("kishan@gmail.com");
        searchDTO.setMobile(1234567890L);
        searchDTO.setActive(true);

        userDetail = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("search_dto_api_response.json"),
                StandardCharsets.UTF_8);
    }

    @Test
    void getUserName() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<UserDetailEntity> userDetailEntities = objectMapper.readValue(userDetail, new TypeReference<List<UserDetailEntity>>() {
        });
        String userName= USERNAME;
        when(searchRepository.findByUserName(userName)).thenReturn(userDetailEntities);
        List<SearchDTO> searchDetailResponse = searchService.getSearchDetail(searchDTO);
        when(searchBarHandler.getUserData()).thenReturn(searchDetailResponse);
        assertEquals(0, searchDetailResponse.size());
    }

    @Test
    void getFirstName() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<UserDetailEntity> userDetailEntities = objectMapper.readValue(userDetail, new TypeReference<List<UserDetailEntity>>() {
        });
        SearchDTO searchDTO1=new SearchDTO();
        String firstName = FIRSTNAME;
        searchDTO1.setFirstName(firstName);
        List<SearchDTO> searchDetailResponse = searchService.getSearchDetail(searchDTO1);
        when(searchBarHandler.getUserData()).thenReturn(searchDetailResponse);
        assertEquals(0, searchDetailResponse.size());
    }

    @Test
    void getLastName() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<UserDetailEntity> userDetailEntities = objectMapper.readValue(userDetail, new TypeReference<List<UserDetailEntity>>() {
        });
        SearchDTO searchDTO1=new SearchDTO();
        String lastName = LASTNAME;
        searchDTO1.setLastName(lastName);
        List<SearchDTO> searchDetailResponse = searchService.getSearchDetail(searchDTO1);
        when(searchBarHandler.getUserData()).thenReturn(searchDetailResponse);
        assertEquals(0, searchDetailResponse.size());
    }

    @Test
    void getEmail() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<UserDetailEntity> userDetailEntities = objectMapper.readValue(userDetail, new TypeReference<List<UserDetailEntity>>() {
        });
        SearchDTO searchDTO1=new SearchDTO();
        String email = EMAIL;
        searchDTO1.setEmail(email);
        List<SearchDTO> searchDetailResponse = searchService.getSearchDetail(searchDTO1);
        when(searchBarHandler.getUserData()).thenReturn(searchDetailResponse);
        assertEquals(0, searchDetailResponse.size());
    }
}