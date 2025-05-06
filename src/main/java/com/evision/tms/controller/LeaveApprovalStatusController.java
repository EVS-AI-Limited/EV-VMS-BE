package com.evision.tms.controller;

import com.evision.tms.dto.LeaveApprovalStatusDTO;
import com.evision.tms.service.LeaveApprovalStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaveApprovalStatus")
public class LeaveApprovalStatusController {
    @Autowired
    private LeaveApprovalStatusService leaveApprovalStatusService;

    @PostMapping("/create")
    LeaveApprovalStatusDTO create(@RequestBody LeaveApprovalStatusDTO leaveApprovalStatusDTO){
        return leaveApprovalStatusService.create(leaveApprovalStatusDTO);
    }

    @PutMapping("/update/{leaveApprovalStatusId}")
    LeaveApprovalStatusDTO update(@PathVariable String leaveApprovalStatusId, @RequestBody LeaveApprovalStatusDTO leaveApprovalStatusDTO){
        return leaveApprovalStatusService.update(Long.valueOf(leaveApprovalStatusId), leaveApprovalStatusDTO);
    }

    @GetMapping("/get/{leaveApprovalStatusId}")
    LeaveApprovalStatusDTO getByLeaveApprovalStatusId(@PathVariable String leaveApprovalStatusId){
        return leaveApprovalStatusService.getByLeaveApprovalStatusId(Long.valueOf(leaveApprovalStatusId));
    }

    @GetMapping("getAll")
    List<LeaveApprovalStatusDTO> getAllLeaveApprovalStatus(){
        return leaveApprovalStatusService.getAllLeaveApprovalStatus();
    }

    @DeleteMapping("/delete/{leaveApprovalStatusId}")
    ResponseEntity<HttpStatus> deleteLeaveApprovalStatus(@PathVariable String leaveApprovalStatusId) {
        try {
            this.leaveApprovalStatusService.deleteLeaveApprovalStatus(Long.valueOf(leaveApprovalStatusId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
