package com.evision.tms.controller;

import com.evision.tms.common.ApiError;
import com.evision.tms.dto.AttendanceDTO;
import com.evision.tms.dto.UserDetailResponseDTO;
import com.evision.tms.service.AttendanceService;
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

import java.text.ParseException;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/v1/user/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @Operation(summary = "get All Attendance detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "data not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/getAll")
    public List<AttendanceDTO> getAllAttendance() {
        log.info("Inside Class: AttendanceController , Method: getAllAttendance");
        return attendanceService.getAllAttendance();
    }

    @Operation(summary = "get Attendance detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "data not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @GetMapping("/{id}")
    public AttendanceDTO getAttendance(@PathVariable String id) {
        log.info("Inside Class: AttendanceController , Method: getAttendance");
        return attendanceService.getAttendance(Integer.parseInt(id));
    }

    @Operation(summary = "create Attendance detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "already exist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @PostMapping("/create/{userId}")
    public AttendanceDTO createAttendance(@PathVariable String userId) throws Exception {
        log.info("Inside Class: AttendanceController , Method: createAttendance()");
        return attendanceService.createAttendance(Integer.parseInt(userId));
    }

    @Operation(summary = "update Attendance detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "data not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @PutMapping("/update/{id}")
    public AttendanceDTO updateAttendance(@PathVariable String id, @RequestBody AttendanceDTO attendanceDTO) throws ParseException {
        log.info("Inside Class: AttendanceController , Method: updateAttendance");
        return attendanceService.updateAttendance(Integer.parseInt(id), attendanceDTO);
    }

    @Operation(summary = "delete Attendance detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "data not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAttendance(@PathVariable String id) {
        log.info("Inside Class: AttendanceController , Method: deleteAttendance");
        try {
            this.attendanceService.deleteAttendance(Integer.parseInt(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}