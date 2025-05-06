package com.evision.tms.repository;

import com.evision.tms.entity.LeaveDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveDetailRepository extends JpaRepository<LeaveDetailEntity,Long> {
}
