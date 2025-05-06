package com.evision.tms.controller;

import com.evision.tms.entity.UserEducationProfile;
import com.evision.tms.dto.UserEducationProfileDTO;
import com.evision.tms.repository.UserEducationRepository;
import com.evision.tms.repository.UserRepository;
import com.evision.tms.service.UserEducationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/education")
public class UserEducationController {
    @Autowired
    private UserEducationService userEducationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserEducationRepository userEducationRepository;

    @GetMapping("/getAll")
    public List<UserEducationProfileDTO> getUserEducationProfileList() {
        log.info("Inside Class: UserEducationController , Method: getUserEducationProfileList");
        return userEducationService.getUserEducationProfileList();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<UserEducationProfileDTO> getByUserId(@PathVariable("id") int userId){
        log.info("Inside Class: UserEducationController , Method: getByUserId");
        Optional<UserEducationProfile> userEducationProfile = userEducationRepository.findById(userId);
        try{
            userEducationProfile.isPresent();
            return new ResponseEntity<>(userEducationService.getUserEducationDetailsById(userId), HttpStatus.OK);

        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public  UserEducationProfileDTO createUserEducationProfile(@RequestBody UserEducationProfileDTO userEducationProfileDTO){
        log.info("Inside Class: UserEducationController , Method: createUserEducationProfile");
        return userEducationService.createUserEducationProfile(userEducationProfileDTO);
    }

    @PutMapping("update/{id}")
    public UserEducationProfileDTO updatedUserEducationProfile(@PathVariable("id") int id, @RequestBody UserEducationProfileDTO userEducationProfileDTO){
        log.info("Inside Class: UserEducationController , Method: updatedUserEducationProfile");
        return userEducationService.updateUserEducationProfile(userEducationProfileDTO,id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteUserEducationDetails(@PathVariable("id")int id){
        log.info("Inside Class: UserEducationController , Method: deleteUserEducationDetails");
        try {
            userEducationService.deleteUserEducationDetails(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}