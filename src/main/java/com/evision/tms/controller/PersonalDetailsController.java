package com.evision.tms.controller;

import com.evision.tms.common.ApiError;
import com.evision.tms.dto.PersonalDetailsDTO;
import com.evision.tms.dto.UserDetailResponseDTO;
import com.evision.tms.service.PersonalDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/user")
@CrossOrigin
public class PersonalDetailsController {
    @Autowired
    private PersonalDetailsService personalDetailsService;

    @Operation(summary = "get All Personal Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "data not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/getAllPersonalDetails")
    public List<PersonalDetailsDTO> getAllPersonalDetails(){
        log.info("Inside Class: PersonalDetailsController , Method: getAllPersonalDetails ");
        return this.personalDetailsService.getPersonalDetails();
    }

    @Operation(summary = "get Personal Detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "data not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/getPersonalDetail/{id}")
    public PersonalDetailsDTO getPersonalDetail(@PathVariable String id){
        log.info("Inside Class: PersonalDetailsController , Method: getPersonalDetail ");
        return this.personalDetailsService.getPersonalDetail(Long.parseLong(id));
    }

    @Operation(summary = "create Personal Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Already Exist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @PostMapping("/createPersonalDetails")
    public PersonalDetailsDTO createPersonalDetails(@RequestBody PersonalDetailsDTO personalDetailsDTO){
        log.info("Inside Class: PersonalDetailsController , Method: createPersonalDetails ");
        return this.personalDetailsService.createPersonalDetails(personalDetailsDTO);
    }

    @Operation(summary = "update Personal Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "data not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @PutMapping("/updatePersonalDetails/{id}")
    public PersonalDetailsDTO updatePersonalDetails(@PathVariable String id,@RequestBody PersonalDetailsDTO personalDetailsDTO){
        log.info("Inside Class: PersonalDetailsController , Method: updatePersonalDetails ");
        return this.personalDetailsService.updatePersonalDetails(Long.parseLong(id),personalDetailsDTO);
    }

    @Operation(summary = "delete Personal Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "data not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @DeleteMapping("/deletePersonalDetails/{id}")
    public ResponseEntity<HttpStatus> deletePersonalDetails(@PathVariable String id) {
        log.info("Inside Class: PersonalDetailsController , Method: deletePersonalDetails ");
        try {
            this.personalDetailsService.deletePersonalDetail(Long.parseLong(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}