package com.suwanee.dto.response;

import com.suwanee.model.entity.Topic;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class TopicResponse {

    private UUID id;
    private UUID userId;
    private String name;
    private String module;
    private List<EntryResponse> entries;

    public static TopicResponse from(Topic topic) {
        TopicResponse response = new TopicResponse();
        response.id = topic.getId();
        response.userId = topic.getUser().getId();
        response.name = topic.getName();
        response.module = topic.getModule();
        response.entries = topic.getEntries()
                .stream()
                .map(EntryResponse::from)
                .toList();
        return response;
    }
}