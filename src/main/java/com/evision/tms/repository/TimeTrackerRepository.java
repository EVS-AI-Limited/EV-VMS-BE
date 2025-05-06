package com.evision.tms.repository;

import com.evision.tms.entity.TimeTrackerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTrackerRepository extends JpaRepository<TimeTrackerEntity,Integer> {

}
