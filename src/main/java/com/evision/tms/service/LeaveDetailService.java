package com.evision.tms.service;

import com.evision.tms.dto.LeaveDetailDTO;

import java.util.List;

public interface LeaveDetailService {
    LeaveDetailDTO create(LeaveDetailDTO leaveDetailDTO);
    LeaveDetailDTO update(Long id, LeaveDetailDTO leaveDetailDTO);
    List<LeaveDetailDTO> getAllLeaveDetails();
    LeaveDetailDTO getLeaveDetail(Long id);
    void deleteLeaveDetail(Long id,LeaveDetailDTO leaveDetailDTO);
}
