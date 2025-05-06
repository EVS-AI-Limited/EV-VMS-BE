package com.evision.tms.service;

import com.evision.tms.dto.DocumentsDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FileSystemStorage {
    void init();
    DocumentsDTO uploadFile(MultipartFile file, String userData);

}
