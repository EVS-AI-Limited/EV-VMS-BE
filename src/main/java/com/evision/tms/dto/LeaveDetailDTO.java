package com.evision.tms.dto;

import com.evision.tms.entity.LeaveTypeEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveDetailDTO {
    private long id;
    private long userId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int duration;
    private String reason;
    private Boolean isDeleted;
    private LeaveTypeEntity leaveTypeEntity;
}
