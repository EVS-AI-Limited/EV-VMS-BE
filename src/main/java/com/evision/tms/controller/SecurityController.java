package com.evision.tms.controller;

import com.evision.tms.common.ApiError;
import com.evision.tms.dto.UserDetailResponseDTO;
import com.evision.tms.service.TokenService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class SecurityController {
    @Autowired
    private TokenService tokenService;

    @Operation(summary = "generate Token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "not generate token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @PostMapping
    @Hidden
    public JSONObject generateToken(@RequestBody String payload) {
        log.info("Inside Class: SecurityController , Method: generateToken()");
        return tokenService.createJwtSignedHMAC(payload);
    }
}
