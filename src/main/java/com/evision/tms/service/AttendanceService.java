package com.evision.tms.service;

import com.evision.tms.dto.AttendanceDTO;

import java.text.ParseException;
import java.util.List;

public interface AttendanceService {
    List<AttendanceDTO> getAllAttendance();
    AttendanceDTO getAttendance(int id);
    AttendanceDTO createAttendance(int userId) throws Exception;
    AttendanceDTO updateAttendance(int userId, AttendanceDTO attendanceDTO) throws ParseException;
    void deleteAttendance(int id);
}