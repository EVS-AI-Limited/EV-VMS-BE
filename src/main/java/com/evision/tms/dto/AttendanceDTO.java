package com.evision.tms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AttendanceDTO {
    private int id;
    private int userId;
    private String loginTime;
    private String logoutTime;
    private String totalTime;
    @JsonIgnore
    private String createdDate;
    @JsonIgnore
    private String updatedDate;
    private Boolean isLogout;
    private String absentOrPresent;

}