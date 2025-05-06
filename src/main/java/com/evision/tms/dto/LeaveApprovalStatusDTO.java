package com.evision.tms.dto;

import lombok.Data;

@Data
public class LeaveApprovalStatusDTO {
    private Long leaveApprovalStatusId;
    private String leaveApprovalStatusTypes;
    private String note;
}
