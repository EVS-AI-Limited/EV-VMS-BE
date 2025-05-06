package com.evision.tms.service.impl;

import com.evision.tms.dto.LeaveTypeDTO;
import com.evision.tms.entity.LeaveTypeEntity;
import com.evision.tms.repository.LeaveTypeRepository;
import com.evision.tms.service.LeaveTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {
    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    private List<LeaveTypeDTO> prepareResponse(List<LeaveTypeEntity> list) {
        List<LeaveTypeDTO> leaveTypeDTOList = new ArrayList<>();
        for (LeaveTypeEntity leaveTypeEntity : list) {
            LeaveTypeDTO leaveTypeDTO = new LeaveTypeDTO();
            BeanUtils.copyProperties(leaveTypeEntity, leaveTypeDTO);
            leaveTypeDTOList.add(leaveTypeDTO);
        }
        return leaveTypeDTOList;
    }

    @Override
    public LeaveTypeDTO createLeaveTypes(LeaveTypeDTO leaveTypeDTO) {
        LeaveTypeEntity leaveTypeEntity = new LeaveTypeEntity();
        BeanUtils.copyProperties(leaveTypeDTO, leaveTypeEntity);
        leaveTypeEntity.setIsDeleted(Boolean.FALSE);
        leaveTypeDTO.setIsDeleted(leaveTypeEntity.getIsDeleted());
        leaveTypeRepository.save(leaveTypeEntity);
        leaveTypeDTO.setLeaveTypeId(leaveTypeEntity.getLeaveTypeId());
        return leaveTypeDTO;
    }

    @Override
    public LeaveTypeDTO updateLeaveTypes(int leaveTypeId, LeaveTypeDTO leaveTypeDTO) {
        leaveTypeDTO.setLeaveTypeId(leaveTypeId);
        LeaveTypeEntity leaveTypeEntity = new LeaveTypeEntity();
        BeanUtils.copyProperties(leaveTypeDTO, leaveTypeEntity);
        LeaveTypeEntity getLeaveTypeId = leaveTypeRepository.getReferenceById(leaveTypeId);
        LeaveTypeEntity save = leaveTypeRepository.save(getLeaveTypeId);
        leaveTypeEntity.setIsDeleted(Boolean.FALSE);
        leaveTypeDTO.setIsDeleted(leaveTypeEntity.getIsDeleted());
        this.leaveTypeRepository.save(leaveTypeEntity);
        leaveTypeDTO.setLeaveTypeId(leaveTypeEntity.getLeaveTypeId());
        return leaveTypeDTO;
    }

    @Override
    public List<LeaveTypeDTO> getAllLeaveTypes() {
        List<LeaveTypeEntity> leaveTypeEntityList = leaveTypeRepository.findAll();
        return prepareResponse(leaveTypeEntityList);
    }

    @Override
    public LeaveTypeDTO getLeaveTypes(int leaveTypeId) {
        LeaveTypeEntity leaveTypeEntity = leaveTypeRepository.getReferenceById(leaveTypeId);
        LeaveTypeDTO leaveTypeDTO = new LeaveTypeDTO();
        BeanUtils.copyProperties(leaveTypeEntity, leaveTypeDTO);
        return leaveTypeDTO;
    }

    @Override
    public void deleteLeaveTypes(int leaveTypeId) {
        LeaveTypeEntity getLeaveTypeId = leaveTypeRepository.getReferenceById(leaveTypeId);
        leaveTypeRepository.delete(getLeaveTypeId);
    }
}
