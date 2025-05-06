package com.evision.tms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "leave_types")
@SQLDelete(sql = "UPDATE leave_types SET is_deleted = TRUE WHERE leave_type_id=?")
@Where(clause = "is_deleted = FALSE")
public class LeaveTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_type_id", nullable = false)
    private int leaveTypeId;

    @Column(name = "leave_types")
    private String leaveTypes;

    @Column(name = "note")
    private String note;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;
}
