package com.ScheduleMaker.ScheduleMaker.Entities;

/**
 * Computed from hoursCompleted vs minHours on Topic (see Topic.getStatus()).
 * Not a persisted column - deriving it means it can never drift out of sync
 * with the actual logged hours.
 */
public enum TopicStatus {
    PENDING,      // hoursCompleted == 0, never started
    IN_PROGRESS,  // 0 < hoursCompleted < minHours, partially logged
    DONE          // hoursCompleted >= minHours, satisfies the scheduler's completion threshold
}
