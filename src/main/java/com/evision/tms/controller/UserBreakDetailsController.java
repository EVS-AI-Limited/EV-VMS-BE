package com.evision.tms.controller;

import com.evision.tms.dto.UserBreakDetailsDTO;
import com.evision.tms.repository.UserBreakDetailsRepository;
import com.evision.tms.service.UserBreakDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/v1/user/break")
public class UserBreakDetailsController {

    @Autowired
    private UserBreakDetailsRepository breakRepository;
    @Autowired
    private UserBreakDetailsService userBreakDetailsService;

    @GetMapping("/getAll")
    public List<UserBreakDetailsDTO> getAllBreak() {
        log.info("Inside Class: UserBreakDetailsController , Method: getAllBreak ");
        return userBreakDetailsService.getAllBreak();
    }

    @GetMapping("/get/{id}")
    public UserBreakDetailsDTO getOneBreak(@PathVariable String id){
        log.info("Inside Class: UserBreakDetailsController , Method: getOneBreak ");
        return userBreakDetailsService.getOneBreak(Long.parseLong(id));
    }

    @PostMapping("/create/{userBreakId}")
    public ResponseEntity<?> createBreak(@PathVariable int userBreakId,@RequestBody UserBreakDetailsDTO userBreakDetailsDTO) {
        try {
            UserBreakDetailsDTO createdBreak = userBreakDetailsService.createBreak(userBreakId,userBreakDetailsDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBreak);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public UserBreakDetailsDTO updateBreak(@PathVariable String id, @RequestBody UserBreakDetailsDTO userBreakDetailsDTO) throws ParseException {
        log.info("Inside Class: UserBreakDetailsController , Method: updateBreak ");
        return userBreakDetailsService.updateBreak(Long.parseLong(id), userBreakDetailsDTO);
    }
}
