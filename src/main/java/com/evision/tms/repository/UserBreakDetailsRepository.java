package com.evision.tms.repository;

import com.evision.tms.entity.UserBreakDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface UserBreakDetailsRepository extends JpaRepository<UserBreakDetails, Long> {
    List<UserBreakDetails> findByUserBreakIdAndBreakInBetween(int userBreakId, LocalTime breakIn, LocalTime breakOut);

    @Query(value = "SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(break_time))) AS total_time FROM user_break_details WHERE user_break_id=?",
            nativeQuery = true)
    Time totalBreakTime(@Param("userBreakId") int userBreakId);

}
