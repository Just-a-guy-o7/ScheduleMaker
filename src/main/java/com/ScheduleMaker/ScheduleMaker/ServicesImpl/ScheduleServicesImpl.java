package com.ScheduleMaker.ScheduleMaker.ServicesImpl;

import com.ScheduleMaker.ScheduleMaker.Entities.Schedule;
import com.ScheduleMaker.ScheduleMaker.Repositories.ScheduleRepo;
import com.ScheduleMaker.ScheduleMaker.Services.ScheduleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleServicesImpl implements ScheduleServices {

    private ScheduleRepo scheduleRepo;

    @Autowired
    public void setScheduleRepo(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    @Override
    public boolean saveSchedule(Schedule toSave) {
        if (toSave != null && toSave.getId() != null && isScheduleById(toSave.getId())) {
            return updateSchedule(toSave);
        }
        try {
            scheduleRepo.save(toSave);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Schedule getSchedule(Long scheduleId) {
        Optional<Schedule> inDB = scheduleRepo.findById(scheduleId);
        return inDB.orElse(null);
    }

    @Override
    public boolean updateSchedule(Schedule toUpdate) {
        if (toUpdate != null && toUpdate.getId() != null && isScheduleById(toUpdate.getId())) {
            scheduleRepo.save(toUpdate);
            return true;
        }
        try {
            scheduleRepo.save(toUpdate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteSchedule(Schedule schedule) {
        if (schedule != null && schedule.getId() != null && isScheduleById(schedule.getId())) {
            try {
                scheduleRepo.delete(schedule);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean deleteScheduleById(Long scheduleId) {
        if (scheduleId != null && isScheduleById(scheduleId)) {
            try {
                scheduleRepo.deleteById(scheduleId);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isScheduleById(Long scheduleId) {
        return scheduleId != null && scheduleRepo.findById(scheduleId).isPresent();
    }
}
