package com.evision.tms.dto;

import lombok.Data;

import java.time.Year;

@Data
public class UserEducationProfileDTO {
    private int id;
    private int userId;
    private String collegeName;
    private String degreeName;
    private String stream;
    private double finalCgpa;
    private String highSchoolName;
    private Year highSchoolPassingYear;
    private String highSchoolPercentage;
    private String secondarySchoolName;
    private Year secondarySchoolPassingYear;
    private String secondarySchoolPercentage;
}
