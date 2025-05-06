package com.evision.tms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "role_entity")
public class RoleEntity {
    @Id
    @Column(name = "RoleId")
    private long roleId;

    @Column(name = "RoleName")
    private String roleName;
}