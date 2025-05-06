package com.evision.tms.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeTrackerDTO {


        private Integer projectId;
        private String projectName;
        private String description;

        private LocalDateTime startTime;
        private LocalDateTime stopTime;

}


