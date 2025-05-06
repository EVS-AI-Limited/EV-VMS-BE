package com.evision.tms.controller;

import com.evision.tms.dto.SearchDTO;
import com.evision.tms.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SearchBy")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/searchDto")
    public List<SearchDTO> getSearchDetail(@RequestBody SearchDTO searchDTO) {
        log.info("Inside Class: SearchController , Method: getSearchDetail");
        return searchService.getSearchDetail(searchDTO);

    }
}