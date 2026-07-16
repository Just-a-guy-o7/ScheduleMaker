package com.ScheduleMaker.ScheduleMaker.ServicesImpl;

import com.ScheduleMaker.ScheduleMaker.Entities.Topic;
import com.ScheduleMaker.ScheduleMaker.Repositories.TopicRepo;
import com.ScheduleMaker.ScheduleMaker.Services.TopicServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicServicesImpl implements TopicServices {

    private TopicRepo topicRepo;

    @Autowired
    public void setTopicRepo(TopicRepo topicRepo) {
        this.topicRepo = topicRepo;
    }

    @Override
    public boolean saveTopic(Topic toSave) {
        if (toSave != null && toSave.getId() != null && isTopicById(toSave.getId())) {
            return updateTopic(toSave);
        }
        try {
            topicRepo.save(toSave);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Topic getTopic(Long topicId) {
        Optional<Topic> inDB = topicRepo.findById(topicId);
        return inDB.orElse(null);
    }

    @Override
    public boolean updateTopic(Topic toUpdate) {
        if (toUpdate != null && toUpdate.getId() != null && isTopicById(toUpdate.getId())) {
            topicRepo.save(toUpdate);
            return true;
        }
        try {
            topicRepo.save(toUpdate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteTopic(Topic topic) {
        if (topic != null && topic.getId() != null && isTopicById(topic.getId())) {
            try {
                topicRepo.delete(topic);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean deleteTopicById(Long topicId) {
        if (topicId != null && isTopicById(topicId)) {
            try {
                topicRepo.deleteById(topicId);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isTopicById(Long topicId) {
        return topicId != null && topicRepo.findById(topicId).isPresent();
    }
}
