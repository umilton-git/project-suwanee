package com.suwanee.controller;

import com.suwanee.dto.request.CreateTopicRequest;
import com.suwanee.dto.request.UpdateTopicRequest;
import com.suwanee.dto.response.TopicResponse;
import com.suwanee.service.TopicService;
import com.suwanee.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/topics")
public class TopicController {
    private final TopicService topicService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopicResponse postTopic(@Valid @RequestBody CreateTopicRequest createTopicRequest) {
        UUID userId = userService.getCurrentUserId();
        return topicService.createTopic(userId, createTopicRequest);
    }
    @GetMapping
    public List<TopicResponse> getTopics(){
        return topicService.getTopicsForUser(userService.getCurrentUserId());
    }

    @PatchMapping("/{topicId}")
    public TopicResponse updateTopic(@PathVariable UUID topicId,
                                     @Valid @RequestBody UpdateTopicRequest request) {
        UUID userId = userService.getCurrentUserId();
        return topicService.updateTopic(topicId, userId, request);
    }

    @DeleteMapping("/{topicId}")
    public void deleteTopic(@PathVariable UUID topicId) {
        UUID userId = userService.getCurrentUserId();
        topicService.deleteTopic(topicId, userId);
    }
}
