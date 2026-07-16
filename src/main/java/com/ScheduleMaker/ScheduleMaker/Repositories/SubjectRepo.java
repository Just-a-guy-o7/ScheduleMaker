package com.ScheduleMaker.ScheduleMaker.Repositories;

import com.ScheduleMaker.ScheduleMaker.Entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<Subject, Long> {
}
