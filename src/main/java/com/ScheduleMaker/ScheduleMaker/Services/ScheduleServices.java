package com.ScheduleMaker.ScheduleMaker.Services;

import com.ScheduleMaker.ScheduleMaker.Entities.Schedule;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleServices {

    /**
     * Saves a new schedule entity.
     */
    boolean saveSchedule(Schedule toSave);

    /**
     * Retrieves a schedule by its identifier.
     */
    Schedule getSchedule(Long scheduleId);

    /**
     * Updates an existing schedule entity.
     */
    boolean updateSchedule(Schedule toUpdate);

    /**
     * Deletes the provided schedule entity.
     */
    boolean deleteSchedule(Schedule schedule);

    /**
     * Deletes a schedule by its identifier.
     */
    boolean deleteScheduleById(Long scheduleId);

    /**
     * Checks whether a schedule exists for the given identifier.
     */
    boolean isScheduleById(Long scheduleId);
}
