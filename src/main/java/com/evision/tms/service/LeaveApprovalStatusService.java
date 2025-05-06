package com.evision.tms.service;

import com.evision.tms.dto.LeaveApprovalStatusDTO;

import java.util.List;

public interface LeaveApprovalStatusService {
    LeaveApprovalStatusDTO create(LeaveApprovalStatusDTO leaveApprovalStatusDTO);
    LeaveApprovalStatusDTO update(Long LeaveApprovalStatusId,LeaveApprovalStatusDTO leaveApprovalStatusDTO);
    LeaveApprovalStatusDTO getByLeaveApprovalStatusId(Long leaveApprovalStatusId);
    List<LeaveApprovalStatusDTO> getAllLeaveApprovalStatus();
    void deleteLeaveApprovalStatus(Long leaveApprovalStatusId);
}
