package com.evision.tms.service.implTest;

import com.evision.tms.dto.AttendanceDTO;
import com.evision.tms.entity.Attendance;
import com.evision.tms.repository.AttendanceRepository;
import com.evision.tms.service.impl.AttendanceServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AttendanceServiceImplTest {
    @InjectMocks
    private AttendanceServiceImpl attendanceService;
    @Mock
    private AttendanceRepository attendanceRepository;
    @Spy
    private ObjectMapper objectMapper;

    private AttendanceDTO attendanceDTO;

    private String attendance;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(1);
        attendanceDTO.setUserId(1);
        attendanceDTO.setLoginTime("10:00:00");
        attendanceDTO.setLogoutTime("19:00:00");
        attendanceDTO.setTotalTime("09:00:00");
        attendanceDTO.setCreatedDate("2023-01-01");
        attendanceDTO.setUpdatedDate("2023-01-01");
        attendanceDTO.setAbsentOrPresent("Present");
        attendanceDTO.setIsLogout(true);

        attendance =
                IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("attendance_api_response.json"),
                        StandardCharsets.UTF_8);
    }

    @Test
    void getAllAttendance() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Attendance> attendances = objectMapper.readValue(attendance, new TypeReference<List<Attendance>>() {
        });
        when(attendanceRepository.findAll()).thenReturn(attendances);
        List<AttendanceDTO> attendanceDTOList = attendanceService.getAllAttendance();
        assertEquals(1, attendanceDTOList.get(0).getUserId());
        assertEquals("10:00:00", attendanceDTOList.get(0).getLoginTime());
        when(attendanceService.getAllAttendance()).thenReturn(attendanceDTOList);
    }

    @Test
    void getAttendance() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Attendance> attendances = objectMapper.readValue(attendance, new TypeReference<List<Attendance>>() {
        });
        when(attendanceRepository.getReferenceById(attendanceDTO.getUserId())).thenReturn(attendances.get(0));
        AttendanceDTO attendanceDTOList = attendanceService.getAttendance(attendanceDTO.getUserId());
        assertEquals(1, attendanceDTOList.getUserId());
        assertEquals("10:00:00", attendanceDTOList.getLoginTime());
    }


    @Test
    void createAttendance() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Attendance> attendances = objectMapper.readValue(attendance, new TypeReference<List<Attendance>>() {
        });
        Mockito.when(attendanceRepository.save(attendances.get(0))).thenReturn(attendances.get(0));
        AttendanceDTO createAttendance = attendanceService.createAttendance(attendanceDTO.getUserId());
        assertEquals("Present", createAttendance.getAbsentOrPresent());
        assertEquals(1, createAttendance.getUserId());
    }

    @Test
    void updateAttendance() throws Exception {
        List<Attendance> attendances = objectMapper.readValue(attendance, new TypeReference<List<Attendance>>() {
        });
        Mockito.when(attendanceRepository.getReferenceById(attendanceDTO.getId())).thenReturn(attendances.get(0));
       when(attendanceRepository.save(attendances.get(0))).thenReturn(attendances.get(0));
        AttendanceDTO updateAttendance = attendanceService.updateAttendance(attendanceDTO.getId(), attendanceDTO);
        assertEquals(1, updateAttendance.getUserId());
        assertEquals("Present", updateAttendance.getAbsentOrPresent());

    }

    @Test
    void deleteAttendance() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Attendance> attendances = objectMapper.readValue(attendance, new TypeReference<List<Attendance>>() {
        });
        Mockito.when(attendanceRepository.getReferenceById(attendanceDTO.getId())).thenReturn(attendances.get(0));
        attendanceRepository.delete(attendances.get(0));
        attendanceService.deleteAttendance(attendanceDTO.getId());
    }
}