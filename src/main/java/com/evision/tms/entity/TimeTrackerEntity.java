package com.evision.tms.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "tracker")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class TimeTrackerEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id",nullable = false)
        private Integer Id;
        @Column(name = "projectId",nullable = false)
        private Integer projectId;
        @Column(name = "projectName",nullable = false)
        private String projectName;
        @UpdateTimestamp
        @Column(name = "startTime",updatable = false,nullable =false )
        private LocalDateTime startTime;
        @UpdateTimestamp
        @Column(name = "stopTime")
        private LocalDateTime stopTime;

        @Column(name = "status",nullable = false)
        private String stopStatus="Inprogress";
        private LocalDateTime duration;
        @Column(name = "taskTitle")
        private String description;
}




