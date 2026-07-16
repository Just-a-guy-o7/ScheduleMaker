package com.ScheduleMaker.ScheduleMaker.Repositories;

import com.ScheduleMaker.ScheduleMaker.Entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepo extends JpaRepository<Topic, Long> {
}
