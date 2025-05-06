package com.evision.tms.dto;

import lombok.Data;

@Data
public class DocumentsDTO {
    private int docId;
    private String filePath;
    private String fileName;
    private String docTypeId;
    private String docType;
    private int userId;
    private Boolean status;
}

