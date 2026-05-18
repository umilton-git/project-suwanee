package com.suwanee.service;

import com.suwanee.model.entity.Entry;
import com.suwanee.model.entity.Topic;
import com.suwanee.repository.EntryRepository;
import com.suwanee.repository.TopicRepository;
import com.suwanee.dto.request.CreateEntryRequest;
import com.suwanee.dto.request.UpdateEntryRequest;
import com.suwanee.dto.response.EntryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class EntryService {

    private final EntryRepository entryRepository;
    private final TopicRepository topicRepository;

    public EntryResponse createEntry(UUID topicId, UUID userId, CreateEntryRequest request) {

        Topic topic = topicRepository.findByIdAndUserId(topicId, userId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        Entry entry = new Entry();
        entry.setTopic(topic);
        entry.setLearned(request.getLearned());
        entry.setInsights(request.getInsights());
        entry.setQuestions(request.getQuestions());
        entry.setConfidence(request.getConfidence());

        return EntryResponse.from(entryRepository.save(entry));
    }

    public List<EntryResponse> getEntriesForTopic(UUID topicId, UUID userId) {

        topicRepository.findByIdAndUserId(topicId, userId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        return entryRepository.findByTopicIdOrderByCreatedAtDesc(topicId)
                .stream()
                .map(EntryResponse::from)
                .toList();
    }

    public EntryResponse updateEntry(UUID entryId, UUID userId, UpdateEntryRequest request) {

        Entry entry = entryRepository.findByIdAndTopicUserId(entryId, userId)
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        request.getLearned().ifPresent(entry::setLearned);
        request.getInsights().ifPresent(entry::setInsights);
        request.getQuestions().ifPresent(entry::setQuestions);
        request.getConfidence().ifPresent(entry::setConfidence);

        return EntryResponse.from(entryRepository.save(entry));
    }

    public void deleteEntry(UUID entryId, UUID userId) {

        Entry entry = entryRepository.findByIdAndTopicUserId(entryId, userId)
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        entryRepository.delete(entry);
    }
}