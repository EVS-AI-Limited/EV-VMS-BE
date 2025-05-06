package com.evision.tms.service.impl;

import com.evision.tms.dto.AccountDetailDTO;
import com.evision.tms.entity.AccountDetailEntity;
import com.evision.tms.repository.AccountDetailRepository;
import com.evision.tms.service.AccountDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AccountDetailServiceImpl implements AccountDetailService {

    @Autowired
    private AccountDetailRepository accountDetailRepository;

    private List<AccountDetailDTO> prepareResponse(List<AccountDetailEntity> list) {
        log.info("Inside Class: AccountDetailServiceImpl , Method: prepareResponse ");
        List<AccountDetailDTO> accountDetailDTOList = new ArrayList<>();
        for (AccountDetailEntity accountDetail : list) {
            AccountDetailDTO accountDetailDto = new AccountDetailDTO();
            BeanUtils.copyProperties(accountDetail, accountDetailDto);
            accountDetailDTOList.add(accountDetailDto);
        }
        return accountDetailDTOList;
    }

    @Override
    public List<AccountDetailDTO> getUserAccountDetails() {
        log.info("Inside Class: AccountDetailServiceImpl , Method: getUserAccountDetails ");
        List<AccountDetailEntity> accountDetails = accountDetailRepository.findAll();
        return prepareResponse(accountDetails);

    }

    @Override
    public AccountDetailDTO getUserAccountDetail(int id) {
        log.info("Inside Class: AccountDetailServiceImpl , Method: getUserAccountDetail ");
        AccountDetailEntity accountDetailEntity = accountDetailRepository.getReferenceById(id);
        AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
        BeanUtils.copyProperties(accountDetailEntity, accountDetailDTO);
        return accountDetailDTO;
    }

    @Override
    public AccountDetailDTO createUserAccountDetail(AccountDetailDTO accountDetailDTO) {
        log.info("Inside Class: AccountDetailServiceImpl , Method: createUserAccountDetail ");
        AccountDetailEntity accountDetailEntity = new AccountDetailEntity();
        BeanUtils.copyProperties(accountDetailDTO, accountDetailEntity);
        accountDetailDTO.setId(accountDetailEntity.getId());
        this.accountDetailRepository.save(accountDetailEntity);
        accountDetailDTO.setId(accountDetailEntity.getId());
        return accountDetailDTO;
    }

    @Override
    public AccountDetailDTO updateUserAccountDetail(int id, AccountDetailDTO accountDetailDTO) {
        log.info("Inside Class: AccountDetailServiceImpl , Method: updateUserAccountDetail ");
         accountDetailDTO.setId(id);
         AccountDetailEntity accountDetailEntity = new AccountDetailEntity();
         BeanUtils.copyProperties(accountDetailDTO, accountDetailEntity);
         AccountDetailEntity getId = accountDetailRepository.getReferenceById(id);
         accountDetailRepository.save(getId);
         accountDetailRepository.save(accountDetailEntity);
         accountDetailDTO.setId(accountDetailEntity.getId());
         return accountDetailDTO;
    }

    @Override
    public void deleteUserAccountDetail(int id) {
        log.info("Inside Class: AccountDetailServiceImpl , Method: deleteUserAccountDetail ");
        AccountDetailEntity accountDetailEntity=accountDetailRepository.getReferenceById(id);
        accountDetailRepository.delete(accountDetailEntity);
    }
}