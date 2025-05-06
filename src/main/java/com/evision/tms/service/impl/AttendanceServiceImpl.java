package com.evision.tms.service.impl;

import com.evision.tms.dto.AttendanceDTO;
import com.evision.tms.entity.Attendance;
import com.evision.tms.repository.AttendanceRepository;
import com.evision.tms.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    private List<AttendanceDTO> prepareResponse(List<Attendance> list) {
        log.info("Inside Class: AttendanceServiceImpl , Method: prepareResponse ");
        List<AttendanceDTO> attendanceDTOS = new ArrayList<>();
        for (Attendance attendance : list) {
            AttendanceDTO attendanceDTO = new AttendanceDTO();
            BeanUtils.copyProperties(attendance, attendanceDTO);
            attendanceDTOS.add(attendanceDTO);
        }
        return attendanceDTOS;
    }

    @Override
    public List<AttendanceDTO> getAllAttendance() {
        log.info("Inside Class: AttendanceServiceImpl , Method: getAllAttendance ");
        List<Attendance> attendances = attendanceRepository.findAll();
        return prepareResponse(attendances);
    }

    @Override
    public AttendanceDTO getAttendance(int id) {
        log.info("Inside Class: AttendanceServiceImpl , Method: getAttendance ");
        Attendance attendance = attendanceRepository.getReferenceById(id);
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        BeanUtils.copyProperties(attendance, attendanceDTO);
        return attendanceDTO;
    }

    public AttendanceDTO createAttendance(int userId) throws Exception{
        log.info("Inside Class: AttendanceServiceImpl , Method: createAttendance ");
        Attendance attendance = new Attendance();
        DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now1 = LocalDateTime.now();
        String localTime = (datetime.format(now1));
        String dateFormat = localTime.substring(0, 10);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String localDateTime = (dtf.format(now));
        String time = localDateTime.substring(11, 19);

        LocalDate currentDate = LocalDate.now();
        Attendance existingEntry = attendanceRepository.findByUserIdAndCreatedDate(userId, String.valueOf(currentDate));
        AttendanceDTO attendanceDTO;
        if (existingEntry == null) {
            attendanceDTO = new AttendanceDTO();
            attendanceDTO.setUserId(userId);
            attendanceDTO.setCreatedDate(dateFormat);
            attendanceDTO.setLoginTime(time);
            attendanceDTO.setUpdatedDate(dateFormat);
            attendanceDTO.setIsLogout(false);
            attendanceDTO.setAbsentOrPresent("Present");
            BeanUtils.copyProperties(attendanceDTO, attendance);
            attendanceRepository.save(attendance);
            attendanceDTO.setId(attendance.getId());
        }else {
            throw new RuntimeException("You Are Already Login Today...");
        }
        return attendanceDTO;
    }

    @Override
    public AttendanceDTO updateAttendance(int id, AttendanceDTO attendanceDTO) throws ParseException {
        log.info("Inside Class: AttendanceServiceImpl , Method: updateAttendance ");
        DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now1 = LocalDateTime.now();
        String localTime = (datetime.format(now1));
        String currentDate = localTime.substring(0, 10);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String localDateTime = (dtf.format(now));
        String currentTime = localDateTime.substring(11, 19);

        if (id == attendanceDTO.getId()) {
            Attendance attendance=attendanceRepository.getReferenceById(attendanceDTO.getId());
            attendanceDTO.setUserId(attendance.getUserId());
            attendanceDTO.setLoginTime(attendance.getLoginTime());
            attendanceDTO.setLogoutTime(currentTime);
            attendanceDTO.setUpdatedDate(currentDate);
            attendanceDTO.setCreatedDate(attendance.getCreatedDate());
            attendanceDTO.setAbsentOrPresent(attendance.getAbsentOrPresent());
            attendanceDTO.setIsLogout(true);
            String time1 = attendanceDTO.getLoginTime();
            String time2 = attendanceDTO.getLogoutTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date1 = simpleDateFormat.parse(time1);
            Date date2 = simpleDateFormat.parse(time2);
            long differenceInMilliSeconds = Math.abs(date2.getTime() - date1.getTime());
            String differenceInHours = String.valueOf(((differenceInMilliSeconds / (60 * 60 * 1000)) % 24));
            String differenceInMinutes = String.valueOf(((differenceInMilliSeconds / (60 * 1000)) % 60));
            String differenceInSeconds = String.valueOf(((differenceInMilliSeconds / 1000) % 60));
            attendanceDTO.setTotalTime(differenceInHours + ":" + differenceInMinutes + ":" + differenceInSeconds);
            BeanUtils.copyProperties(attendanceDTO, attendance);
            attendanceRepository.save(attendance);
            return attendanceDTO;
        } else throw new RuntimeException("ID Does Not Have Exist In DataBase");
    }

    @Override
    public void deleteAttendance(int id) {
        log.info("Inside Class: AttendanceServiceImpl , Method: deleteAttendance ");
        Attendance getId = attendanceRepository.getReferenceById(id);
        this.attendanceRepository.delete(getId);
    }
}