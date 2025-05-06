package com.evision.tms.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "documents")
@Entity
public class DocumentsEntity {
    @Id
    @Column(name = "doc_id")
    private int docId;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "doc_type_id")
    private String docTypeId;
    @Column(name = "doc_type")
    private String docType;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "status")
    private Boolean status;

    public DocumentsEntity(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DocumentsEntity entity = mapper.readValue(jsonString, DocumentsEntity.class);
        this.userId = entity.getUserId();
        this.fileName = entity.getFileName();
        this.filePath = entity.getFilePath();
        this.docTypeId = entity.getDocTypeId();
        this.docType = entity.getDocType();
        this.docId = entity.getDocId();
        this.status = entity.getStatus();
    }
}
