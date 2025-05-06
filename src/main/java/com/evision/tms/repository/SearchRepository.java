package com.evision.tms.repository;

import com.evision.tms.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<UserDetailEntity,Integer> {
    @Query(value = "Select * From evision.user_detail where user_name = ? ", nativeQuery = true)
    List<UserDetailEntity> findByUserName(@Param("userName") String userName);

    @Query(value = "Select * From evision.user_detail where official_email_id = ? ", nativeQuery = true)
    List<UserDetailEntity> findByEmail(@Param("email") String email);

    @Query(value = "Select * From evision.user_detail where first_name = ? ", nativeQuery = true)
    List<UserDetailEntity> findByFirstName(@Param("firstName") String fistName);

    @Query(value = "Select * From evision.user_detail where last_name = ? ", nativeQuery = true)
    List<UserDetailEntity> findByLastName(@Param("lastName") String lastName);

}
