package com.evision.tms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "personal_details")
@SQLDelete(sql = "UPDATE personal_details SET is_deleted = TRUE WHERE id=?")
@Where(clause = "is_deleted = FALSE")
public class PersonalDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "father_contact_number")
    private String fatherContactNumber;
    @Column(name = "mother_name")
    private String motherName;
    @Column(name = "current_address")
    private String currentAddress;
    @Column(name = "permanent_address")
    private String permanentAddress;
    @Email
    @Column(name = "personal_mail_id")
    private String personalMailId;
    @Column(name = "personal_contact_number")
    private String personalContactNumber;
    @Column(name = "aadhar_number")
    private Long aadharNumber;
    @Column(name = "pan_number")
    private String panNumber;
}
