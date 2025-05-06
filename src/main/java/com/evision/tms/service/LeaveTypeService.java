package com.evision.tms.service;

import com.evision.tms.dto.LeaveTypeDTO;

import java.util.List;

public interface LeaveTypeService {
    LeaveTypeDTO createLeaveTypes(LeaveTypeDTO leaveTypeDTO);
    LeaveTypeDTO updateLeaveTypes(int leaveTypeId, LeaveTypeDTO leaveTypeDTO);
    List<LeaveTypeDTO> getAllLeaveTypes();
    LeaveTypeDTO getLeaveTypes(int leaveTypeId);
    void deleteLeaveTypes(int leaveTypeId);
}
