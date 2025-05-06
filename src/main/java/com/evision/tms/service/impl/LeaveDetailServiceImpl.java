package com.evision.tms.service.impl;

import com.evision.tms.constants.UserConstants;
import com.evision.tms.dto.LeaveDetailDTO;
import com.evision.tms.entity.LeaveDetailEntity;
import com.evision.tms.repository.LeaveDetailRepository;
import com.evision.tms.service.LeaveDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
@Service
public class LeaveDetailServiceImpl implements LeaveDetailService {
    @Autowired
    private LeaveDetailRepository leaveDetailRepository;

    private List<LeaveDetailDTO> prepareResponse(List<LeaveDetailEntity> list) {
        List<LeaveDetailDTO> leaveDetailDTOList = new ArrayList<>();
        for (LeaveDetailEntity leaveDetailEntity : list) {
            LeaveDetailDTO leaveDetailDTOResponse = new LeaveDetailDTO();
            BeanUtils.copyProperties(leaveDetailEntity, leaveDetailDTOResponse);
            leaveDetailDTOList.add(leaveDetailDTOResponse);
        }
        return leaveDetailDTOList;
    }

    @Override
    public LeaveDetailDTO create(LeaveDetailDTO leaveDetailDTO) {
        LeaveDetailEntity leaveDetailEntity = new LeaveDetailEntity();
        BeanUtils.copyProperties(leaveDetailDTO, leaveDetailEntity);

        LocalDate date1 = leaveDetailDTO.getFromDate();
        LocalDate date2 = leaveDetailDTO.getToDate();
        Period differenceBetween = Period.between(date1, date2);
        int totalDays = differenceBetween.getDays();
        int differenceInDays = totalDays + UserConstants.ONE;

        leaveDetailEntity.setDuration(differenceInDays);
        leaveDetailEntity.setIsDeleted(Boolean.FALSE);
        leaveDetailDTO.setIsDeleted(leaveDetailEntity.getIsDeleted());
        leaveDetailRepository.save(leaveDetailEntity);
        leaveDetailDTO.setId(leaveDetailEntity.getId());
        leaveDetailDTO.setDuration(leaveDetailEntity.getDuration());
        return leaveDetailDTO;
    }

    @Override
    public LeaveDetailDTO update(Long id, LeaveDetailDTO leaveDetailDTO) {
        leaveDetailDTO.setId(id);
        LeaveDetailEntity leaveDetailEntity = new LeaveDetailEntity();
        BeanUtils.copyProperties(leaveDetailDTO, leaveDetailEntity);
        LeaveDetailEntity getId = leaveDetailRepository.getReferenceById(id);
        LeaveDetailEntity save = leaveDetailRepository.save(getId);

        LocalDate date1 = leaveDetailDTO.getFromDate();
        LocalDate date2 = leaveDetailDTO.getToDate();
        Period differenceBetween = Period.between(date1, date2);
        int totalDays = differenceBetween.getDays();
        int differenceInDays = totalDays + UserConstants.ONE;

        leaveDetailEntity.setDuration(differenceInDays);
        leaveDetailEntity.setIsDeleted(Boolean.FALSE);
        leaveDetailDTO.setIsDeleted(leaveDetailEntity.getIsDeleted());
        leaveDetailRepository.save(leaveDetailEntity);
        leaveDetailDTO.setId(leaveDetailEntity.getId());
        leaveDetailDTO.setDuration(leaveDetailEntity.getDuration());
        return leaveDetailDTO;
    }

    @Override
    public List<LeaveDetailDTO> getAllLeaveDetails() {
        List<LeaveDetailEntity> leaveDetailEntityList = leaveDetailRepository.findAll();
        return prepareResponse(leaveDetailEntityList);
    }

    @Override
    public LeaveDetailDTO getLeaveDetail(Long id) {
        LeaveDetailEntity leaveDetail = leaveDetailRepository.getReferenceById(id);
        LeaveDetailDTO leaveDetailDTO = new LeaveDetailDTO();
        BeanUtils.copyProperties(leaveDetail,leaveDetailDTO );
        return leaveDetailDTO;
    }

    @Override
    public void deleteLeaveDetail(Long id,LeaveDetailDTO leaveDetailDTO) {
        LeaveDetailEntity getId = leaveDetailRepository.getReferenceById(id);
        leaveDetailRepository.delete(getId);
    }
}
