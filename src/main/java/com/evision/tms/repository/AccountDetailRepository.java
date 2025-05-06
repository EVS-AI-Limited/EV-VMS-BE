package com.evision.tms.repository;

import com.evision.tms.entity.AccountDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailRepository extends JpaRepository<AccountDetailEntity,Integer> {
}
