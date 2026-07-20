package com.ScheduleMaker.ScheduleMaker.ServicesImpl;

import com.ScheduleMaker.ScheduleMaker.Entities.DailyPlan;
import com.ScheduleMaker.ScheduleMaker.Repositories.DailyPlanRepo;
import com.ScheduleMaker.ScheduleMaker.Services.DailyPlanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DailyPlanServicesImpl implements DailyPlanServices {

    private DailyPlanRepo dailyPlanRepo;

    @Autowired
    public void setDailyPlanRepo(DailyPlanRepo dailyPlanRepo) {
        this.dailyPlanRepo = dailyPlanRepo;
    }

    @Override
    public boolean saveDailyPlan(DailyPlan toSave) {
        if (toSave != null && toSave.getId() != null && isDailyPlanById(toSave.getId())) {
            return updateDailyPlan(toSave);
        }
        try {
            dailyPlanRepo.save(toSave);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public DailyPlan getDailyPlan(Long dailyPlanId) {
        Optional<DailyPlan> inDB = dailyPlanRepo.findById(dailyPlanId);
        return inDB.orElse(null);
    }

    @Override
    public boolean updateDailyPlan(DailyPlan toUpdate) {
        if (toUpdate != null && toUpdate.getId() != null && isDailyPlanById(toUpdate.getId())) {
            dailyPlanRepo.save(toUpdate);
            return true;
        }
        try {
            dailyPlanRepo.save(toUpdate);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteDailyPlan(DailyPlan dailyPlan) {
        if (dailyPlan != null && dailyPlan.getId() != null && isDailyPlanById(dailyPlan.getId())) {
            try {
                dailyPlanRepo.delete(dailyPlan);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean deleteDailyPlanById(Long dailyPlanId) {
        if (dailyPlanId != null && isDailyPlanById(dailyPlanId)) {
            try {
                dailyPlanRepo.deleteById(dailyPlanId);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isDailyPlanById(Long dailyPlanId) {
        return dailyPlanId != null && dailyPlanRepo.findById(dailyPlanId).isPresent();
    }
}
