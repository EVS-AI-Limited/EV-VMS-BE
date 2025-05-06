package com.evision.tms.repository;

import com.evision.tms.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetailEntity,Integer> {
   UserDetailEntity findByEmail(String email);
}
