package com.evision.tms.repository;

import com.evision.tms.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    Attendance findByUserIdAndCreatedDate(Integer userId, String createdDate);
}