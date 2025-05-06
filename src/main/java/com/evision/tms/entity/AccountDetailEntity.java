package com.evision.tms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_account_details")
@Setter
@Getter
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE user_account_details SET is_deleted = TRUE WHERE id=?")
@Where(clause = "is_deleted = FALSE")
public class AccountDetailEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="user_id")
    private int userId;
    @Column(name="bank_name")
    private String bankName;
    @Column(name="account_type")
    private String accountType;
    @Column(name="ifsc_code")
    private String ifscCode;
    @Column(name=" account_number")
    private String accountNumber;
    @Column(name="account_holder_name")
    private String accountHolderName;
    @Column(name="bank_address")
    private String bankAddress;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @Column(name="updated_by")
    private String updatedBy;
    @Column(name="updated_date")
    private LocalDateTime updatedDate;
}
