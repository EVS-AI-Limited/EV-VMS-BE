package com.evision.tms.service.impl;

import com.evision.tms.dto.UserBreakDetailsDTO;
import com.evision.tms.entity.TMSConfig;
import com.evision.tms.entity.UserBreakDetails;
import com.evision.tms.repository.TMSConfigRepository;
import com.evision.tms.repository.UserBreakDetailsRepository;
import com.evision.tms.service.UserBreakDetailsService;
import com.evision.tms.utils.DateTimeHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserBreakDetailsServiceImpl implements UserBreakDetailsService {
    @Autowired
    private UserBreakDetailsRepository userBreakDetailsRepository;
    @Autowired
    private TMSConfigRepository tmsConfigRepository;
    @Autowired
    private DateTimeHandler dateTimeHandler;

    private List<UserBreakDetailsDTO> prepareResponse(List<UserBreakDetails> list) {
        log.info("Inside Class: UserBreakDetailsServiceImpl , Method: prepareResponse ");
        List<UserBreakDetailsDTO> userBreakDetailsDTOList = new ArrayList<>();
        for (UserBreakDetails userBreakDetails : list) {
            UserBreakDetailsDTO userBreakDetailsDTO = new UserBreakDetailsDTO();
            BeanUtils.copyProperties(userBreakDetails, userBreakDetailsDTO);
            userBreakDetailsDTOList.add(userBreakDetailsDTO);
        }
        return userBreakDetailsDTOList;
    }

    @Override
    public List<UserBreakDetailsDTO> getAllBreak() {
        log.info("Inside Class: UserBreakDetailsServiceImpl , Method: getAllBreak ");
        List<UserBreakDetails> userBreakDetails = userBreakDetailsRepository.findAll();
        return prepareResponse(userBreakDetails);
    }

    @Override
    public UserBreakDetailsDTO getOneBreak(long id) {
        log.info("Inside Class: UserBreakDetailsServiceImpl , Method: getOneBreak ");
        UserBreakDetails userBreakDetails = userBreakDetailsRepository.getReferenceById(id);
        UserBreakDetailsDTO userBreakDetailsDTO = new UserBreakDetailsDTO();
        BeanUtils.copyProperties(userBreakDetails, userBreakDetailsDTO);
        return userBreakDetailsDTO;
    }

    @Override
    public UserBreakDetailsDTO createBreak(int userBreakId, UserBreakDetailsDTO userBreakDetailsDTO) throws Exception {
        log.info("Inside Class: UserBreakDetailsServiceImpl , Method: createBreak ");
        userBreakDetailsDTO.setUserBreakId(userBreakId);
        List<TMSConfig> tmsConfig = this.tmsConfigRepository.findAll();
        UserBreakDetails userBreakDetails = new UserBreakDetails();
        LocalDate today = LocalDate.now();
        LocalTime startOfToday = LocalTime.from(LocalDateTime.of(today, LocalTime.MIN));
        LocalTime endOfToday = LocalTime.from(LocalDateTime.of(today, LocalTime.MAX));
        List<UserBreakDetails> todayBreaks = userBreakDetailsRepository.findByUserBreakIdAndBreakInBetween(userBreakDetailsDTO.getUserBreakId(), startOfToday, endOfToday);
        for (TMSConfig config : tmsConfig) {
            Long time = Long.valueOf(config.getTotalBreaks());
            if (todayBreaks.size() >= time) {
                throw new Exception("User has already taken three breaks today");
            }
            Time breakTime = config.getBreakAfterOneBreak();
            Date timeDate = new Date(breakTime.getTime());
            int hours = timeDate.getHours();
            int minutes = timeDate.getMinutes();
            int totalMinutes = (hours * 60) + minutes;
            LocalTime nowTime = LocalTime.from(LocalDateTime.now().plusHours(5).plusMinutes(30));
            LocalTime afterMinusTime = nowTime.minusMinutes(totalMinutes);
            List<UserBreakDetails> lastMinuteBreaks = userBreakDetailsRepository.findByUserBreakIdAndBreakInBetween(userBreakDetailsDTO.getUserBreakId(),
                    afterMinusTime, LocalTime.from(LocalDateTime.now().plusHours(5).plusMinutes(30)));
            if (lastMinuteBreaks.size() >= 1) {
                throw new Exception("User has already taken a break in the last hour");
            }
            Time totalBreakTime = config.getTotalTimeForBreak();
            Date getTimeDate = new Date(totalBreakTime.getTime());
            int getHours = getTimeDate.getHours();
            int getMinutes = getTimeDate.getMinutes();
            int getSeconds = getTimeDate.getSeconds();
            int totalTime = (getHours * 60) + getMinutes + getSeconds;

            Time getTotalBreakTime = userBreakDetailsRepository.totalBreakTime(userBreakId);
            int totalTimeTaken = 0;
            if (getTotalBreakTime != null) {
                Date getTime = new Date(getTotalBreakTime.getTime());
                int getHour = getTime.getHours();
                int getMinute = getTime.getMinutes();
                int getSecond = getTime.getSeconds();
                totalTimeTaken = (getHour * 60) + getMinute + getSecond;
            }
            if ((getTotalBreakTime == null) || (totalTimeTaken <= totalTime)) {
                userBreakDetailsDTO.setUserBreakId(userBreakId);
                userBreakDetailsDTO.setBreakIn(dateTimeHandler.getCurrentTime());
                userBreakDetailsDTO.setBreakInOrOut("BreakIn");
                userBreakDetailsDTO.setCreatedDate(dateTimeHandler.getCurrentDate());
                userBreakDetailsDTO.setUpdatedDate(dateTimeHandler.getCurrentDate());
                BeanUtils.copyProperties(userBreakDetailsDTO, userBreakDetails);
                userBreakDetailsRepository.save(userBreakDetails);
                userBreakDetailsDTO.setId(userBreakDetails.getId());
                userBreakDetailsDTO.setUserBreakId(userBreakId);
                return userBreakDetailsDTO;
            } else {
                throw new RuntimeException("you have exhausted total Breaks of the day ");
            }
        }
        return null;
    }

    @Override
    public UserBreakDetailsDTO updateBreak(long id, UserBreakDetailsDTO userBreakDetailsDTO) {
        log.info("Inside Class: UserBreakDetailsServiceImpl , Method: updateBreak ");
        UserBreakDetails userBreakDetails;
        userBreakDetailsDTO.setId(id);
        userBreakDetails = userBreakDetailsRepository.getReferenceById(userBreakDetailsDTO.getId());
        userBreakDetailsDTO.setBreakIn(userBreakDetails.getBreakIn());
        userBreakDetailsDTO.setUserBreakId(userBreakDetails.getUserBreakId());
        userBreakDetailsDTO.setBreakOut(dateTimeHandler.getCurrentTime());
        userBreakDetailsDTO.setBreakReason(userBreakDetails.getBreakReason());
        userBreakDetailsDTO.setBreakInOrOut("BreakOut");
        userBreakDetailsDTO.setCreatedDate(userBreakDetails.getCreatedDate());
        userBreakDetailsDTO.setUpdatedDate(dateTimeHandler.getCurrentDate());
        LocalTime time1 = userBreakDetails.getBreakIn();
        LocalTime time2 = userBreakDetailsDTO.getBreakOut();
        if (userBreakDetails.getBreakTime() == null) {
            long hours = ChronoUnit.HOURS.between(time1, time2);
            long minutes = ChronoUnit.MINUTES.between(time1, time2) % 60;
            long seconds = ChronoUnit.SECONDS.between(time1, time2) % 60;
            int hour = (int) hours;
            int minute = (int) minutes;
            int second = (int) seconds;
            LocalTime time = LocalTime.of(hour, minute, second);
            userBreakDetailsDTO.setBreakTime(time);
            BeanUtils.copyProperties(userBreakDetailsDTO, userBreakDetails);
            userBreakDetailsRepository.save(userBreakDetails);
        } else {
            log.info("You can't make changes to yours Previous BreakInfo");
        }
        return userBreakDetailsDTO;
    }
}