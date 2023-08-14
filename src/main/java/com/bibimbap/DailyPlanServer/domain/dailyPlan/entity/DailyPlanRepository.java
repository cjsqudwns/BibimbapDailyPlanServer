package com.bibimbap.DailyPlanServer.domain.dailyPlan.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {
    boolean existsDailyPlanByYearMonthAndDay(String yearMonth, int day);
    List<DailyPlan> findByYearMonth(String yearMonth);
}
