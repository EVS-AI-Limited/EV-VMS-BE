package com.evision.tms.dto;

import lombok.Data;

@Data
public class LeaveTypeDTO {
    private int leaveTypeId;
    private String leaveTypes;
    private String note;
    private Boolean isDeleted;
}
