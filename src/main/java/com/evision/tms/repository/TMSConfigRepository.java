package com.evision.tms.repository;

import com.evision.tms.entity.TMSConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TMSConfigRepository extends JpaRepository<TMSConfig,Long> {

}
