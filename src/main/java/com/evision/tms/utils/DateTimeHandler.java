package com.evision.tms.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Configuration
@Slf4j
public class DateTimeHandler {
    public LocalTime getCurrentTime() {
        log.info("Inside Class : DateTimeHandler , Method : getCurrentTime");
        LocalTime time = LocalTime.now();
        LocalTime laterTime = time.plusHours(5).plusMinutes(30);
        return laterTime;
    }
    public LocalDateTime getCurrentDate() {
        log.info("Inside Class : DateTimeHandler , Method : getCurrentDate");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime laterDateTime = now.plusHours(5).plusMinutes(30);
        return laterDateTime;
    }
}
