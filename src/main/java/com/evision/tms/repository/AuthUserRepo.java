package com.evision.tms.repository;

import com.evision.tms.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepo extends JpaRepository<UserDetailEntity, Integer> {
    
    UserDetailEntity findByUserName(@Param("userName")String userName);
}
