package com.ScheduleMaker.ScheduleMaker.Entities;


public enum ScheduleStatus {
    ACTIVE,     // currently being worked on and included in daily plan generation
    COMPLETED,  // end date reached / all topics done - kept for history, no longer scheduled
    ARCHIVED    // user manually hid it (e.g. abandoned) - excluded from active dashboards
}
