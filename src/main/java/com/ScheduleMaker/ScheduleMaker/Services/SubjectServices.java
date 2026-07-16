package com.ScheduleMaker.ScheduleMaker.Services;

import com.ScheduleMaker.ScheduleMaker.Entities.Subject;
import org.springframework.stereotype.Service;

@Service
public interface SubjectServices {

    /**
     * Saves a new subject entity.
     */
    boolean saveSubject(Subject toSave);

    /**
     * Retrieves a subject by its identifier.
     */
    Subject getSubject(Long subjectId);

    /**
     * Updates an existing subject entity.
     */
    boolean updateSubject(Subject toUpdate);

    /**
     * Deletes the provided subject entity.
     */
    boolean deleteSubject(Subject subject);

    /**
     * Deletes a subject by its identifier.
     */
    boolean deleteSubjectById(Long subjectId);

    /**
     * Checks whether a subject exists for the given identifier.
     */
    boolean isSubjectById(Long subjectId);
}
