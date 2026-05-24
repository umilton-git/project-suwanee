package com.suwanee.controller;

import com.suwanee.dto.request.CreateEntryRequest;
import com.suwanee.dto.request.UpdateEntryRequest;
import com.suwanee.dto.response.EntryResponse;
import com.suwanee.service.EntryService;
import com.suwanee.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/topics/{topicId}/entries")
public class EntryController {

    private final EntryService entryService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntryResponse createEntry(@PathVariable UUID topicId, @Valid @RequestBody CreateEntryRequest createEntryRequest) {
        UUID userId = userService.getCurrentUserId();
        return entryService.createEntry(topicId, userId, createEntryRequest);
    }

    @GetMapping
    public List<EntryResponse> getEntries(@PathVariable UUID topicId) {
        return entryService.getEntriesForTopic(topicId, userService.getCurrentUserId());
    }

    @PatchMapping("/{entryId}")
    public EntryResponse updateEntry(@PathVariable UUID topicId, @PathVariable UUID entryId, @Valid @RequestBody UpdateEntryRequest updateEntryRequest) {
        UUID userId = userService.getCurrentUserId();
        return entryService.updateEntry(entryId, userId, updateEntryRequest);
    }

    @DeleteMapping("/{entryId}")
    public void deleteEntry(@PathVariable UUID topicId, @PathVariable UUID entryId) {
        UUID userId = userService.getCurrentUserId();
        entryService.deleteEntry(entryId, userId);
    }
}
