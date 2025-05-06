package com.evision.tms.controller;

import com.evision.tms.common.ApiError;
import com.evision.tms.dto.AccountDetailDTO;
import com.evision.tms.dto.UserDetailResponseDTO;
import com.evision.tms.service.AccountDetailService;
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
@RequestMapping("/accountsDetail")
@CrossOrigin
@Slf4j
@Tag(name = "user_account_details", description = "AccountDetail related operation")
public class AccountDetailController {

    @Autowired
    private AccountDetailService accountDetailService;

    @Operation(summary = "get All Account detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "data not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/getAll")
    public List<AccountDetailDTO> getUserAccountDetails() {
        log.info("Inside Class: AccountDetailController , Method: getUserAccountDetails");
        return accountDetailService.getUserAccountDetails();
    }

    @Operation(summary = "create Account detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "File already exist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @PostMapping("/createAccountDetail")
    public AccountDetailDTO createUserAccountDetail(@RequestBody AccountDetailDTO accountDetailDTO) {
        log.info("Inside Class: AccountDetailController , Method: createUserAccountDetail ");
        return accountDetailService.createUserAccountDetail(accountDetailDTO);
    }
    @Operation(summary = "update Account Detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User profile details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = " already exist Account detail",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @PutMapping("/updateAccountDetail/{id}")
    public AccountDetailDTO updateUserAccountDetail(@PathVariable String id, @RequestBody AccountDetailDTO accountDetailDTO) {
        log.info("Inside Class: AccountDetailController , Method: updateUserAccountDetail ");
        return accountDetailService.updateUserAccountDetail(Integer.parseInt(id), accountDetailDTO);
    }

    @Operation(summary = "get Account Detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile Details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = " data not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/getAccountDetail/{id}")
    public AccountDetailDTO getAccountDetail(@PathVariable String id){
        log.info("Inside Class: AccountDetailController , Method: getAccountDetail ");
        return this.accountDetailService.getUserAccountDetail(Integer.parseInt(id));
    }

    @Operation(summary = "delete Account Detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile Details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = " data not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @DeleteMapping("/deleteAccountDetail/{id}")
    public ResponseEntity<HttpStatus> deleteAccountDetail(@PathVariable String id) {
        log.info("Inside Class: AccountDetailController , Method: deleteAccountDetail ");
        try {
            this.accountDetailService.deleteUserAccountDetail(Integer.parseInt(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
