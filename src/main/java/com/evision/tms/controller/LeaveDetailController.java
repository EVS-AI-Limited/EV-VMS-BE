package com.evision.tms.controller;

import com.evision.tms.dto.LeaveDetailDTO;
import com.evision.tms.service.LeaveDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaveDetail")
public class LeaveDetailController {
    @Autowired
    private LeaveDetailService leaveDetailService;

    @PostMapping("/create")
    LeaveDetailDTO createLeaveDetail(@RequestBody LeaveDetailDTO leaveDetailDTO){
        return this.leaveDetailService.create(leaveDetailDTO);
    }

    @PutMapping("/update/{id}")
    LeaveDetailDTO updateLeaveDetail(@PathVariable String id,@RequestBody LeaveDetailDTO leaveDetailDTO){
        return this.leaveDetailService.update(Long.valueOf(id), leaveDetailDTO);
    }

    @GetMapping("/getAll")
    List<LeaveDetailDTO> getAllLeaveDetails(){
        return this.leaveDetailService.getAllLeaveDetails();
    }

    @GetMapping("/get/{id}")
    LeaveDetailDTO getLeaveDetail(@PathVariable String id){
        return this.leaveDetailService.getLeaveDetail(Long.valueOf(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteLeaveType(@PathVariable String id,@RequestBody LeaveDetailDTO leaveDetailDTO) {
        try {
            this.leaveDetailService.deleteLeaveDetail(Long.valueOf(id),leaveDetailDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
