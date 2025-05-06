package com.evision.tms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_break_details")
public class UserBreakDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_break_id")
    private int userBreakId;
    @Column(name = "break_in")
    private LocalTime breakIn;
    @Column(name = "break_out")
    private LocalTime breakOut;
    @Column(name = "break_reason")
    private String breakReason;
    @Column(name = "break_time")
    private LocalTime breakTime;
    @Column(name = "break_in_or_out")
    private String breakInOrOut;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

}
