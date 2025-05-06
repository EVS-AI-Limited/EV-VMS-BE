package com.evision.tms.repository;

import com.evision.tms.dto.ProjectDTO;
import com.evision.tms.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity,Integer > {
}
