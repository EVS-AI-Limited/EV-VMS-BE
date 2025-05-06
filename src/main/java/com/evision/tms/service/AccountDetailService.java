package com.evision.tms.service;

import com.evision.tms.dto.AccountDetailDTO;
import java.util.List;

public interface AccountDetailService {
    List<AccountDetailDTO> getUserAccountDetails();

    AccountDetailDTO getUserAccountDetail(int id);

    AccountDetailDTO createUserAccountDetail(AccountDetailDTO accountDetail);

    AccountDetailDTO updateUserAccountDetail(int id,AccountDetailDTO accountDetail);

    void deleteUserAccountDetail(int id);
}
