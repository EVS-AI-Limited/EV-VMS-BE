package com.evision.tms.controller;

import com.evision.tms.common.ApiError;
import com.evision.tms.dto.DocumentsDTO;
import com.evision.tms.dto.UserDetailResponseDTO;
import com.evision.tms.service.FileSystemStorage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Tag(name = "Document Upload", description = "File Upload operation")
@Slf4j
public class FileController {
    @Autowired
    private FileSystemStorage fileSystemStorage;


    @Operation(summary = "User Document upload")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Profile Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "File already exist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    @PostMapping("/uploadfile")
    public DocumentsDTO uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userData") String userData) {
        log.info("Inside Class: FileController , Method: uploadFile()");
        log.info("add user request");
        return fileSystemStorage.uploadFile(file, userData);
    }
}
