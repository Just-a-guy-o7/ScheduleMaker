package com.ScheduleMaker.ScheduleMaker.ServicesImpl;

import com.ScheduleMaker.ScheduleMaker.Entities.DailyTask;
import com.ScheduleMaker.ScheduleMaker.Repositories.DailyTaskRepo;
import com.ScheduleMaker.ScheduleMaker.Services.DailyTaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DailyTaskServicesImpl implements DailyTaskServices {

    private DailyTaskRepo dailyTaskRepo;

    @Autowired
    public void setDailyTaskRepo(DailyTaskRepo dailyTaskRepo) {
        this.dailyTaskRepo = dailyTaskRepo;
    }

    @Override
    public boolean saveDailyTask(DailyTask toSave) {
        if (toSave != null && toSave.getId() != null && isDailyTaskById(toSave.getId())) {
            return updateDailyTask(toSave);
        }
        try {
            dailyTaskRepo.save(toSave);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public DailyTask getDailyTask(Long dailyTaskId) {
        Optional<DailyTask> inDB = dailyTaskRepo.findById(dailyTaskId);
        return inDB.orElse(null);
    }

    @Override
    public boolean updateDailyTask(DailyTask toUpdate) {
        if (toUpdate != null && toUpdate.getId() != null && isDailyTaskById(toUpdate.getId())) {
            dailyTaskRepo.save(toUpdate);
            return true;
        }
        try {
            dailyTaskRepo.save(toUpdate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteDailyTask(DailyTask dailyTask) {
        if (dailyTask != null && dailyTask.getId() != null && isDailyTaskById(dailyTask.getId())) {
            try {
                dailyTaskRepo.delete(dailyTask);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean deleteDailyTaskById(Long dailyTaskId) {
        if (dailyTaskId != null && isDailyTaskById(dailyTaskId)) {
            try {
                dailyTaskRepo.deleteById(dailyTaskId);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isDailyTaskById(Long dailyTaskId) {
        return dailyTaskId != null && dailyTaskRepo.findById(dailyTaskId).isPresent();
    }
}
