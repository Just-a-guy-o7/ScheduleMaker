package com.ScheduleMaker.ScheduleMaker.Repositories;

import com.ScheduleMaker.ScheduleMaker.Entities.DailyTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyTaskRepo extends JpaRepository<DailyTask, Long> {
}
