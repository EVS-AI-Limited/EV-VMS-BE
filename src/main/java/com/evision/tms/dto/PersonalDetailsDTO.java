package com.evision.tms.dto;

import lombok.Data;

@Data
public class PersonalDetailsDTO {
    private Long id;
    private String fatherName;
    private String fatherContactNumber;
    private String motherName;
    private String currentAddress;
    private String permanentAddress;
    private String personalMailId;
    private String personalContactNumber;
    private Long aadharNumber;
    private String panNumber;
}
