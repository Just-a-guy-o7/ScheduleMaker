package com.ScheduleMaker.ScheduleMaker.Repositories;

import com.ScheduleMaker.ScheduleMaker.Entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
}
