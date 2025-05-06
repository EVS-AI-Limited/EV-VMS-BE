package com.evision.tms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "leave_approval_status")
public class LeaveApprovalStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_approval_status_id", nullable = false)
    private Long leaveApprovalStatusId;
    @Column(name = "leave_approval_status_types")
    private String leaveApprovalStatusTypes;
    @Column(name = "note")
    private String note;
}
