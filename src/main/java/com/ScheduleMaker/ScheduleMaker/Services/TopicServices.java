package com.ScheduleMaker.ScheduleMaker.Services;

import com.ScheduleMaker.ScheduleMaker.Entities.Topic;
import org.springframework.stereotype.Service;

@Service
public interface TopicServices {

    /**
     * Saves a new topic entity.
     */
    boolean saveTopic(Topic toSave);

    /**
     * Retrieves a topic by its identifier.
     */
    Topic getTopic(Long topicId);

    /**
     * Updates an existing topic entity.
     */
    boolean updateTopic(Topic toUpdate);

    /**
     * Deletes the provided topic entity.
     */
    boolean deleteTopic(Topic topic);

    /**
     * Deletes a topic by its identifier.
     */
    boolean deleteTopicById(Long topicId);

    /**
     * Checks whether a topic exists for the given identifier.
     */
    boolean isTopicById(Long topicId);
}
