package com.evision.tms.controller;

import com.evision.tms.dto.LeaveTypeDTO;
import com.evision.tms.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaveType")
public class LeaveTypeController {
    @Autowired
    private LeaveTypeService leaveTypeService;

    @PostMapping("/create")
    LeaveTypeDTO createLeaveTypes(@RequestBody LeaveTypeDTO leaveTypeDTO) {
        return leaveTypeService.createLeaveTypes(leaveTypeDTO);
    }

    @PutMapping("/update/{leaveTypeId}")
    LeaveTypeDTO updateLeaveTypes(@PathVariable String leaveTypeId, @RequestBody LeaveTypeDTO leaveTypeDTO) {
        return leaveTypeService.updateLeaveTypes(Integer.parseInt(leaveTypeId), leaveTypeDTO);
    }

    @GetMapping("/getAll")
    List<LeaveTypeDTO> getAllLeaveTypes() {
        return leaveTypeService.getAllLeaveTypes();
    }

    @GetMapping("/get/{leaveTypeId}")
    LeaveTypeDTO getLeaveType(@PathVariable String leaveTypeId) {
        return leaveTypeService.getLeaveTypes(Integer.parseInt(leaveTypeId));
    }

    @DeleteMapping("/delete/{leaveTypeId}")
    public ResponseEntity<HttpStatus> deleteLeaveType(@PathVariable String leaveTypeId) {
        try {
            this.leaveTypeService.deleteLeaveTypes(Integer.parseInt(leaveTypeId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
