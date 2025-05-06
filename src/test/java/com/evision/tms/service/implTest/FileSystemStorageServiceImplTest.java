package com.evision.tms.service.implTest;

import com.evision.tms.config.FileUploadProperties;
import com.evision.tms.dto.DocumentsDTO;
import com.evision.tms.entity.DocumentsEntity;
import com.evision.tms.exceptionhandler.FileStorageException;
import com.evision.tms.repository.DocumentRepository;
import com.evision.tms.service.impl.FileSystemStorageServiceImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FileSystemStorageServiceImplTest {
    @InjectMocks
    private FileSystemStorageServiceImpl fileSystemStorageService;
    @Mock
    private DocumentRepository documentRepository;
    @Spy
    private ObjectMapper objectMapper;
    @Mock
    private DocumentsDTO documentsDTO;
    private MultipartFile multipartFile;
    @Mock
    private FileUploadProperties fileUploadProperties;
    private String documentsEntityList;
    @Mock
    private Path dirLocation;
    private String userData;
    private DocumentsEntity documentsEntity;
    FileSystemStorageServiceImplTest() {
    }

    @BeforeEach
    void setUp() throws IOException {
        fileUploadProperties = new FileUploadProperties();
        fileUploadProperties.setLocation(String.valueOf(dirLocation));
        fileSystemStorageService = new FileSystemStorageServiceImpl(fileUploadProperties);
        MockitoAnnotations.openMocks(this);
        documentsDTO = new DocumentsDTO();
        documentsDTO.setUserId(1);
        documentsDTO.setFileName("multipartFile");
        documentsDTO.setFilePath(String.valueOf(dirLocation));
        documentsDTO.setDocTypeId("12345");
        documentsDTO.setDocType("Aadhaar");
        documentsDTO.setDocId(123);
        documentsDTO.setStatus(true);

        documentsEntityList = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("file_upload_documents_api_response.json"),
                StandardCharsets.UTF_8);
        documentsEntity = objectMapper.readValue(documentsEntityList, DocumentsEntity.class);
    }

    @Test
    void uploadFile() throws FileStorageException, IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        when(documentRepository.save(any(DocumentsEntity.class))).thenReturn(documentsEntity);
        String name = documentsDTO.getFileName();
        String originalFilename = "Test123.txt";
        String contentType = "text/plain";
        String content = String.valueOf(dirLocation);
        InputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        MockMultipartFile file = new MockMultipartFile(name, originalFilename, contentType, inputStream);
        DocumentsDTO documents = fileSystemStorageService.uploadFile(file, objectMapper.writeValueAsString(documentsEntityList));
        assertEquals(0, documents.getUserId());
        assertEquals(false, documents.getStatus());
    }

    @Test
    void init() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        fileSystemStorageService.init();
    }
}