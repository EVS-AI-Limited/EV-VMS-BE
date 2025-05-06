package com.evision.tms.service.implTest;

import com.evision.tms.dto.AccountDetailDTO;
import com.evision.tms.entity.AccountDetailEntity;
import com.evision.tms.repository.AccountDetailRepository;
import com.evision.tms.service.impl.AccountDetailServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.nio.charset.Charset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AccountDetailServiceImplTest {
    @InjectMocks
    private AccountDetailServiceImpl accountDetailService;
    @Mock
    private AccountDetailRepository accountDetailRepository;
    @Spy
    private ObjectMapper objectMapper;

    private AccountDetailDTO accountDetailDTO;

    private String accountDetail;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountDetailDTO = new AccountDetailDTO();
        accountDetailDTO.setBankName("Hdfc");
        accountDetailDTO.setAccountType("current");
        accountDetailDTO.setIfscCode("abc45");
        accountDetailDTO.setAccountNumber("45551114455");
        accountDetailDTO.setAccountHolderName("Abc");
        accountDetailDTO.setBankAddress("123,vivid nagar");

        accountDetail = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("account_detail_api_response.json"),
                Charset.forName("UTF-8")
        );
    }

    @Test
    void getUserAccountDetails() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<AccountDetailEntity> accountDetailEntities = objectMapper.readValue(accountDetail, new TypeReference<List<AccountDetailEntity>>() {
        });
        when(accountDetailRepository.findAll()).thenReturn(accountDetailEntities);
        List<AccountDetailDTO> accountDetailResponse = accountDetailService.getUserAccountDetails();
        assertEquals("Bank of India", accountDetailResponse.get(0).getBankName());
        assertEquals("Ankit", accountDetailResponse.get(0).getAccountHolderName());

    }
    @Test
    void createUserAccountDetail() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<AccountDetailEntity> accountDetailEntities = objectMapper.readValue(accountDetail, new TypeReference<List<AccountDetailEntity>>() {
        });
        Mockito.when(accountDetailRepository.save(accountDetailEntities.get(0))).thenReturn(accountDetailEntities.get(0));
        AccountDetailDTO accountDetailResponse = accountDetailService.createUserAccountDetail(accountDetailDTO);
        assertEquals("Hdfc", accountDetailResponse.getBankName());
        assertEquals("Abc", accountDetailResponse.getAccountHolderName());
    }

    @Test
    void updateUserAccountDetail() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<AccountDetailEntity> accountDetailEntities = objectMapper.readValue(accountDetail, new TypeReference<List<AccountDetailEntity>>() {
        });
        Mockito.when(accountDetailRepository.save(accountDetailEntities.get(0))).thenReturn(accountDetailEntities.get(0));
        AccountDetailDTO accountDetailResponse = accountDetailService.updateUserAccountDetail(accountDetailDTO.getId(),accountDetailDTO);
        assertEquals("Hdfc", accountDetailResponse.getBankName());
        assertEquals("Abc", accountDetailResponse.getAccountHolderName());
    }

    @Test
    void getUserAccountDetail() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<AccountDetailEntity> userDetailEntities = objectMapper.readValue(accountDetail, new TypeReference<List<AccountDetailEntity>>() {
        });
        when(accountDetailRepository.getReferenceById(accountDetailDTO.getId())).thenReturn(userDetailEntities.get(0));
        AccountDetailDTO accountDetailResponse = accountDetailService.getUserAccountDetail(accountDetailDTO.getId());
        assertEquals("Bank of India", accountDetailResponse.getBankName());
        assertEquals("Ankit", accountDetailResponse.getAccountHolderName());
    }

    @Test
    void deleteUserAccountDetail() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<AccountDetailEntity> accountDetailEntities = objectMapper.readValue(accountDetail, new TypeReference<List<AccountDetailEntity>>() {
        });
        Mockito.when(accountDetailRepository.getReferenceById(accountDetailDTO.getId())).thenReturn(accountDetailEntities.get(0));
        accountDetailRepository.delete(accountDetailEntities.get(0));
        accountDetailService.deleteUserAccountDetail(accountDetailDTO.getId());
    }
}



