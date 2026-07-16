package com.ScheduleMaker.ScheduleMaker.Services;

import com.ScheduleMaker.ScheduleMaker.Entities.DailyPlan;
import org.springframework.stereotype.Service;

@Service
public interface DailyPlanServices {

    /**
     * Saves a new daily plan entity.
     */
    boolean saveDailyPlan(DailyPlan toSave);

    /**
     * Retrieves a daily plan by its identifier.
     */
    DailyPlan getDailyPlan(Long dailyPlanId);

    /**
     * Updates an existing daily plan entity.
     */
    boolean updateDailyPlan(DailyPlan toUpdate);

    /**
     * Deletes the provided daily plan entity.
     */
    boolean deleteDailyPlan(DailyPlan dailyPlan);

    /**
     * Deletes a daily plan by its identifier.
     */
    boolean deleteDailyPlanById(Long dailyPlanId);

    /**
     * Checks whether a daily plan exists for the given identifier.
     */
    boolean isDailyPlanById(Long dailyPlanId);
}
