package com.evision.tms.service.impl;

import com.evision.tms.dto.LeaveApprovalStatusDTO;
import com.evision.tms.entity.LeaveApprovalStatus;
import com.evision.tms.repository.LeaveApprovalStatusRepository;
import com.evision.tms.service.LeaveApprovalStatusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveApprovalStatusServiceImpl implements LeaveApprovalStatusService {
    @Autowired
    private LeaveApprovalStatusRepository leaveApprovalStatusRepository;

    private List<LeaveApprovalStatusDTO> prepareResponse(List<LeaveApprovalStatus> list) {
        List<LeaveApprovalStatusDTO> leaveApprovalStatusDTOList = new ArrayList<>();
        for (LeaveApprovalStatus leaveApprovalStatus : list) {
            LeaveApprovalStatusDTO leaveApprovalStatusDTO = new LeaveApprovalStatusDTO();
            BeanUtils.copyProperties(leaveApprovalStatus, leaveApprovalStatusDTO);
            leaveApprovalStatusDTOList.add(leaveApprovalStatusDTO);
        }
        return leaveApprovalStatusDTOList;
    }

    @Override
    public LeaveApprovalStatusDTO create(LeaveApprovalStatusDTO leaveApprovalStatusDTO) {
        LeaveApprovalStatus leaveApprovalStatus = new LeaveApprovalStatus();
        BeanUtils.copyProperties(leaveApprovalStatusDTO, leaveApprovalStatus);
        leaveApprovalStatusRepository.save(leaveApprovalStatus);
        leaveApprovalStatusDTO.setLeaveApprovalStatusId(leaveApprovalStatus.getLeaveApprovalStatusId());
        return leaveApprovalStatusDTO;
    }

    @Override
    public LeaveApprovalStatusDTO update(Long leaveApprovalStatusId,LeaveApprovalStatusDTO leaveApprovalStatusDTO) {
        leaveApprovalStatusDTO.setLeaveApprovalStatusId(leaveApprovalStatusId);
        LeaveApprovalStatus leaveApprovalStatus = new LeaveApprovalStatus();
        BeanUtils.copyProperties(leaveApprovalStatusDTO, leaveApprovalStatus);
        LeaveApprovalStatus getLeaveApprovalStatusId = leaveApprovalStatusRepository.getReferenceById(leaveApprovalStatusId);
        LeaveApprovalStatus save = leaveApprovalStatusRepository.save(getLeaveApprovalStatusId);
        leaveApprovalStatusRepository.save(leaveApprovalStatus);
        leaveApprovalStatusDTO.setLeaveApprovalStatusId(leaveApprovalStatus.getLeaveApprovalStatusId());
        return leaveApprovalStatusDTO;
    }

    @Override
    public LeaveApprovalStatusDTO getByLeaveApprovalStatusId(Long leaveApprovalStatusId) {
        LeaveApprovalStatus leaveApprovalStatus = leaveApprovalStatusRepository.getReferenceById(leaveApprovalStatusId);
        LeaveApprovalStatusDTO leaveApprovalStatusDTO = new LeaveApprovalStatusDTO();
        BeanUtils.copyProperties(leaveApprovalStatus,leaveApprovalStatusDTO);
        return leaveApprovalStatusDTO;
    }

    @Override
    public List<LeaveApprovalStatusDTO> getAllLeaveApprovalStatus() {
        List<LeaveApprovalStatus> leaveApprovalStatus = leaveApprovalStatusRepository.findAll();
        return prepareResponse(leaveApprovalStatus);
    }

    @Override
    public void deleteLeaveApprovalStatus(Long leaveApprovalStatusId) {
        LeaveApprovalStatus getById = leaveApprovalStatusRepository.getReferenceById(leaveApprovalStatusId);
        leaveApprovalStatusRepository.delete(getById);
    }
}
