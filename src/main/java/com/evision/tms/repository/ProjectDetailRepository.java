package com.evision.tms.repository;

import com.evision.tms.entity.ManualTimeSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectDetailRepository extends JpaRepository<ManualTimeSheetEntity, Integer> {

}
