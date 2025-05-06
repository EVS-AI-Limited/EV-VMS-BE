package com.evision.tms.service.impl;

import com.evision.tms.dto.SearchDTO;
import com.evision.tms.entity.UserDetailEntity;
import com.evision.tms.repository.SearchRepository;
import com.evision.tms.service.SearchService;
import com.evision.tms.utils.SearchBarHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    public static List<SearchDTO> searchDTOList = new ArrayList<>();
    @Autowired
    private SearchRepository searchRepository;
    @Autowired
    private SearchBarHandler searchBarHandler;
    public static List<UserDetailEntity> userDetail= new ArrayList<>();
    @Override
    public List<SearchDTO> getSearchDetail(SearchDTO searchDTO) {
        log.info("Inside Class: SearchServiceImpl , Method: getSearchDetail");
        if (searchDTO.getUserName()!= null) {
            String userName = searchDTO.getUserName();
            searchDTOList.clear();
            userDetail = searchRepository.findByUserName(userName);
            searchBarHandler.getUserData();
        } else if (searchDTO.getEmail() != null) {
            String email=searchDTO.getEmail();
            searchDTOList.clear();
            userDetail = searchRepository.findByEmail(email);
            searchBarHandler.getUserData();
        } else if (searchDTO.getFirstName()!= null) {
            String firstName=searchDTO.getFirstName();
            searchDTOList.clear();
            userDetail = searchRepository.findByFirstName(firstName);
            searchBarHandler.getUserData();
        } else if (searchDTO.getLastName() != null) {
            String lastName= searchDTO.getLastName();
            searchDTOList.clear();
            userDetail = searchRepository.findByLastName(lastName);
            searchBarHandler.getUserData();
        }
        return searchDTOList;
    }
}