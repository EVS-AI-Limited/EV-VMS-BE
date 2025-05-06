package com.evision.tms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AccountDetailDTO {
    @JsonIgnore
    @NotNull
    private int id;
    @JsonIgnore
    private int userId;
    @NotNull
    private String bankName;
    @NotNull
    private String accountType;
    @NotNull
    private String ifscCode;
    @NotNull
    private String accountNumber;
    @NotNull
    private String accountHolderName;
    @NotNull
    private String bankAddress;
    @JsonIgnore
    private String createdBy;
    @JsonIgnore
    private LocalDateTime createdDate;
    @JsonIgnore
    private String updatedBy;
    @JsonIgnore
    private LocalDateTime updatedDate;
}
