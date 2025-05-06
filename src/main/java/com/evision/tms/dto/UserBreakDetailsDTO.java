package com.evision.tms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class UserBreakDetailsDTO {
    private Long id;
    private int userBreakId;
    private LocalTime breakIn;
    private LocalTime breakOut;
    private String breakReason;
    private LocalTime breakTime;
    private String breakInOrOut;
    @JsonIgnore
    private LocalDateTime createdDate;
    @JsonIgnore
    private LocalDateTime updatedDate;

}
