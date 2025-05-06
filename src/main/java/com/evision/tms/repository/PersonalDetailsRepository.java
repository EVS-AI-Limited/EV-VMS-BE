package com.evision.tms.repository;

import com.evision.tms.entity.PersonalDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDetailsRepository extends JpaRepository<PersonalDetailsEntity,Long> {
}
