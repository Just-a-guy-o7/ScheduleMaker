package com.ScheduleMaker.ScheduleMaker.ServicesImpl;

import com.ScheduleMaker.ScheduleMaker.Entities.Subject;
import com.ScheduleMaker.ScheduleMaker.Repositories.SubjectRepo;
import com.ScheduleMaker.ScheduleMaker.Services.SubjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectServicesImpl implements SubjectServices {

    private SubjectRepo subjectRepo;

    @Autowired
    public void setSubjectRepo(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    @Override
    public boolean saveSubject(Subject toSave) {
        if (toSave != null && toSave.getId() != null && isSubjectById(toSave.getId())) {
            return updateSubject(toSave);
        }
        try {
            subjectRepo.save(toSave);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Subject getSubject(Long subjectId) {
        Optional<Subject> inDB = subjectRepo.findById(subjectId);
        return inDB.orElse(null);
    }

    @Override
    public boolean updateSubject(Subject toUpdate) {
        if (toUpdate != null && toUpdate.getId() != null && isSubjectById(toUpdate.getId())) {
            subjectRepo.save(toUpdate);
            return true;
        }
        try {
            subjectRepo.save(toUpdate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteSubject(Subject subject) {
        if (subject != null && subject.getId() != null && isSubjectById(subject.getId())) {
            try {
                subjectRepo.delete(subject);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean deleteSubjectById(Long subjectId) {
        if (subjectId != null && isSubjectById(subjectId)) {
            try {
                subjectRepo.deleteById(subjectId);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSubjectById(Long subjectId) {
        return subjectId != null && subjectRepo.findById(subjectId).isPresent();
    }
}
