package com.evision.tms.service.impl;

import com.evision.tms.config.FileUploadProperties;
import com.evision.tms.dto.DocumentsDTO;
import com.evision.tms.entity.DocumentsEntity;
import com.evision.tms.exceptionhandler.FileStorageException;
import com.evision.tms.repository.DocumentRepository;
import com.evision.tms.service.FileSystemStorage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class FileSystemStorageServiceImpl implements FileSystemStorage {
    private final Path dirLocation;
    DocumentsDTO documentsDTO = new DocumentsDTO();
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    public FileSystemStorageServiceImpl(FileUploadProperties fileUploadProperties) {
        log.info("Inside Class: FileSystemStorageServiceImpl , Method: FileSystemStorageServiceImpl ");
        this.dirLocation = Paths.get(fileUploadProperties.getLocation()).toAbsolutePath()
                        .normalize();
    }

    @Override
    @PostConstruct
    public void init() {
        log.info("Inside Class: FileSystemStorageServiceImpl , Method: init() ");
        try {
            Files.createDirectories(this.dirLocation); }
        catch (Exception ex) { throw new FileStorageException(ex.getMessage()); }}

    @Override
    public DocumentsDTO uploadFile(MultipartFile file, String userData) {
        log.info("Inside Class: FileSystemStorageServiceImpl , Method: uploadFile ");
        try {
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            Path fileSource = this.dirLocation.resolve(fileName);
            Files.copy(file.getInputStream(), fileSource, StandardCopyOption.REPLACE_EXISTING);
            try {
                if (file != null || userData != null) {
                    DocumentsEntity documentsEntity = new ObjectMapper().readValue(userData, DocumentsEntity.class);
                    String docTypeId = documentsEntity.getDocTypeId();
                    String docType = documentsEntity.getDocType();
                    Boolean status = documentsEntity.getStatus();

                    documentsDTO.setFileName(fileName);
                    documentsDTO.setFilePath(String.valueOf(dirLocation));
                    documentsDTO.setDocTypeId(docTypeId);
                    documentsDTO.setDocType(docType);
                    documentsDTO.setStatus(status);
                }
            } catch (Exception e) { throw new FileStorageException(e); }
            return createUserDocument(documentsDTO);
        } catch (Exception e) { throw new FileStorageException(e); }
    }

    private DocumentsDTO createUserDocument(DocumentsDTO documentsDTO) {
        log.info("Inside Class: FileSystemStorageServiceImpl , Method: createUserDocument ");
        DocumentsEntity documentsEntity = new DocumentsEntity();
        BeanUtils.copyProperties(documentsDTO, documentsEntity);
        this.documentRepository.save(documentsEntity);
        documentsDTO.setUserId(documentsEntity.getUserId());
        return documentsDTO;}
}
