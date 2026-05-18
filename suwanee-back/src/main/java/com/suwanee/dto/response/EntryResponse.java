package com.suwanee.dto.response;

import com.suwanee.model.entity.Entry;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EntryResponse {

    private UUID id;
    private UUID topicId;
    private String learned;
    private String insights;
    private String questions;
    private Integer confidence;
    private LocalDateTime createdAt;

    public static EntryResponse from(Entry entry) {
        EntryResponse response = new EntryResponse();
        response.id = entry.getId();
        response.topicId = entry.getTopic().getId();
        response.learned = entry.getLearned();
        response.insights = entry.getInsights();
        response.questions = entry.getQuestions();
        response.confidence = entry.getConfidence();
        response.createdAt = entry.getCreatedAt();
        return response;
    }
}