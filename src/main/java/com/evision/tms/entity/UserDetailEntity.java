package com.evision.tms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "user_detail")
@Setter
@Getter
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE user_detail SET is_active = FALSE WHERE user_id=?")
@Where(clause = "is_active=true")
public class UserDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @NotNull
    private int userId;
    @Column(name = "user_name",unique = true)
    private String userName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Email
    @Column(name = "official_email_id",unique = true)
    private String email;
    @Column(name = "mobile")
    private Long mobile;

}
