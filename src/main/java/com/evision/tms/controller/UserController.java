package com.evision.tms.controller;

import com.evision.tms.common.ApiError;
import com.evision.tms.dto.UserDetailDTO;
import com.evision.tms.dto.UserDetailRequestDTO;
import com.evision.tms.dto.UserDetailResponseDTO;
import com.evision.tms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
@Tag(name = "User Details", description = "Registration related operation")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "List of All User ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "User Unauthorized",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/getAll")
    public List<UserDetailResponseDTO> getUserDetails() {
        log.info("Inside Class: UserController , Method: getUserDetails ");
        return userService.getUserDetails();
    }

    @Operation(summary = "get only 1 user ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "User Unauthorized",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/{userId}")
    public UserDetailResponseDTO getUserDetail(@PathVariable String userId) {
        log.info("Inside Class: UserController , Method: getUserDetail ");
        return userService.getUserDetail(Integer.parseInt(userId));
    }

    @Operation(summary = "Save Personal Information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personal information Saved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserController.class))}),
            @ApiResponse(responseCode = "401", description = "User Unauthorized",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @PostMapping("/register")
    public UserDetailResponseDTO registerUser(@RequestBody UserDetailRequestDTO requestDetail) {
        log.info("Inside Class: UserController , Method: registerUser ");
        return userService.registerUser(requestDetail);
    }

    @Operation(summary = "Update Personal Information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personal information Updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserController.class))}),
            @ApiResponse(responseCode = "401", description = "User Unauthorized",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @PutMapping("/update/{userId}")
    public UserDetailResponseDTO updateUser(@PathVariable String userId, @RequestBody UserDetailDTO userDetailDTO) {
        log.info("Inside Class: UserController , Method: updateUser ");
        return userService.updateUserDetail(Integer.parseInt(userId), userDetailDTO);
    }

    @Operation(summary = "Delete Personal Information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personal information Deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserController.class))}),
            @ApiResponse(responseCode = "401", description = "User Unauthorized",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String userId) {
        log.info("Inside Class: UserController , Method: deleteUser ");
        try {
            this.userService.deleteUserDetail(Integer.parseInt(userId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
