package com.evision.tms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.Year;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_education_profile")
@SQLDelete(sql="Update user_education_profile SET is_deleted = TRUE WHERE id =?")
@Where(clause ="is_deleted = false")
public class UserEducationProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "college_name")
    private String collegeName;

    @Column(name = "degree_name ")
    private String degreeName;

    @Column(name = "stream")
    private String stream;

    @Column(name = "final_CGPA")
    private double finalCgpa;

    @Column(name = "high_school_name")
    private String highSchoolName;

    @Column(name = "high_school_passing_year")
    private Year highSchoolPassingYear;

    @Column(name = "high_school_percentage")
    private String highSchoolPercentage;

    @Column(name = "secondary_school_name")
    private String secondarySchoolName;

    @Column(name = "secondary_school_passing_year")
    private Year secondarySchoolPassingYear;

    @Column(name = "secondary_school_percentage")
    private String secondarySchoolPercentage;
}
