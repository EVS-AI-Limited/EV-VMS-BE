package com.evision.tms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leave_details")
@SQLDelete(sql = "UPDATE leave_details SET is_deleted = TRUE WHERE id=?")
@Where(clause = "is_deleted = FALSE")
public class LeaveDetailEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "duration")
    private int duration;

    @Column(name = "reason")
    private String reason;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "leave_type_id")
    private LeaveTypeEntity leaveTypeEntity;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;
}