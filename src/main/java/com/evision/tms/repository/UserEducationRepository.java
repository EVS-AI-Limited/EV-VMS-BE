package com.evision.tms.repository;

import com.evision.tms.entity.UserEducationProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEducationRepository extends JpaRepository<UserEducationProfile, Integer> {

}
