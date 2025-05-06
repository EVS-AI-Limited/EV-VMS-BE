package com.evision.tms.repository;

import com.evision.tms.entity.LeaveApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveApprovalStatusRepository extends JpaRepository<LeaveApprovalStatus,Long> {
}
