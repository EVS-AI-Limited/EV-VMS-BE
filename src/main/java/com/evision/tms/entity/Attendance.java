package com.evision.tms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance")
@SQLDelete(sql = "UPDATE attendance SET is_deleted = TRUE Where id=?")
@Where(clause = "is_deleted=FALSE")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "login_time")
    private String loginTime;
    @Column(name = "logout_time")
    private String logoutTime;
    @Column(name = "total_time")
    private String totalTime;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "updated_date")
    private String updatedDate;
    @Column(name = "is_logout")
    private Boolean isLogout;
    @Column(name = "absent_or_present")
    private String absentOrPresent;
}