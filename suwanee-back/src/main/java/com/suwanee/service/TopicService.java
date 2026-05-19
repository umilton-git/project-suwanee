package com.suwanee.service;

import com.suwanee.model.entity.Topic;
import com.suwanee.model.entity.User;
import com.suwanee.repository.TopicRepository;
import com.suwanee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional

public class TopicService {

    private final TopicRepository topicRepository;
    public final UserRepository userRepository;

    public Topic createTopic(UUID userId, String name, String module) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Topic topic = new Topic();
        topic.setUser(user);
        topic.setName(name);
        topic.setModule(module);

        return topicRepository.save(topic);
    }

    public List<Topic> getTopicsForUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return topicRepository.findByUserId(userId);
    }

    public Topic updateTopic(UUID topicId, UUID userId, String name, String module) {
        Topic topic = topicRepository.findByIdAndUserId(topicId, userId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        if (name != null) topic.setName(name);
        if (module != null) topic.setModule(module);

        return topicRepository.save(topic);
    }

    public void deleteTopic(UUID topicId, UUID userId) {
        Topic topic = topicRepository.findByIdAndUserId(topicId, userId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        topicRepository.delete(topic);
    }
}