package com.ScheduleMaker.ScheduleMaker.Services;

import com.ScheduleMaker.ScheduleMaker.Entities.DailyTask;
import org.springframework.stereotype.Service;

@Service
public interface DailyTaskServices {

    /**
     * Saves a new daily task entity.
     */
    boolean saveDailyTask(DailyTask toSave);

    /**
     * Retrieves a daily task by its identifier.
     */
    DailyTask getDailyTask(Long dailyTaskId);

    /**
     * Updates an existing daily task entity.
     */
    boolean updateDailyTask(DailyTask toUpdate);

    /**
     * Deletes the provided daily task entity.
     */
    boolean deleteDailyTask(DailyTask dailyTask);

    /**
     * Deletes a daily task by its identifier.
     */
    boolean deleteDailyTaskById(Long dailyTaskId);

    /**
     * Checks whether a daily task exists for the given identifier.
     */
    boolean isDailyTaskById(Long dailyTaskId);
}
