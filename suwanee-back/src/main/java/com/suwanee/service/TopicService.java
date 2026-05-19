package com.suwanee.service;

import com.suwanee.model.entity.Topic;
import com.suwanee.model.entity.User;
import com.suwanee.repository.TopicRepository;
import com.suwanee.repository.UserRepository;
import com.suwanee.dto.request.CreateTopicRequest;
import com.suwanee.dto.request.UpdateTopicRequest;
import com.suwanee.dto.response.TopicResponse;
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
    private final UserRepository userRepository;

    public TopicResponse createTopic(UUID userId, CreateTopicRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Topic topic = new Topic();
        topic.setUser(user);
        topic.setName(request.getName());
        topic.setModule(request.getModule());

        return TopicResponse.from(topicRepository.save(topic));
    }

    public List<TopicResponse> getTopicsForUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return topicRepository.findByUserId(userId)
                .stream()
                .map(TopicResponse::from)
                .toList();
    }

    public TopicResponse updateTopic(UUID topicId, UUID userId, UpdateTopicRequest request) {
        Topic topic = topicRepository.findByIdAndUserId(topicId, userId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        request.getName().ifPresent(topic::setName);
        request.getModule().ifPresent(topic::setModule);

        return TopicResponse.from(topicRepository.save(topic));
    }

    public void deleteTopic(UUID topicId, UUID userId) {
        Topic topic = topicRepository.findByIdAndUserId(topicId, userId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        topicRepository.delete(topic);
    }
}