package com.ScheduleMaker.ScheduleMaker.Repositories;

import com.ScheduleMaker.ScheduleMaker.Entities.DailyPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyPlanRepo extends JpaRepository<DailyPlan, Long> {
}
