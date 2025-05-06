package com.evision.tms.utils;

import com.evision.tms.dto.SearchDTO;
import com.evision.tms.entity.UserDetailEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.evision.tms.service.impl.SearchServiceImpl.searchDTOList;
import static com.evision.tms.service.impl.SearchServiceImpl.userDetail;
@Slf4j
@Configuration
public class SearchBarHandler {
    public List<SearchDTO> getUserData(){
        log.info("Inside Class: SearchBarHandler , Method: getUserData");
        for(int i= userDetail.size(); i<= userDetail.size();i++) {
            for (UserDetailEntity user : userDetail) {
                SearchDTO search = new SearchDTO();
                search.setUserName(user.getUserName());
                search.setFirstName(user.getFirstName());
                search.setLastName(user.getLastName());
                search.setUserId(user.getUserId());
                search.setEmail(user.getEmail());
                search.setMobile(user.getMobile());
                searchDTOList.add(search);
            }
        }
        return searchDTOList;
    }
}
