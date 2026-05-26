package com.suwanee.controller;

import com.suwanee.dto.request.CreateReviewRequest;
import com.suwanee.dto.request.UpdateReviewRequest;
import com.suwanee.dto.response.ReviewResponse;
import com.suwanee.service.ReviewService;
import com.suwanee.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/topics/{topicId}/entries/{entryId}/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse createReview(@PathVariable UUID entryId, @Valid @RequestBody CreateReviewRequest request) {
        UUID userId = userService.getCurrentUserId();
        return reviewService.createReview(entryId, userId, request);
    }

    @GetMapping
    public List<ReviewResponse> getReviewsForEntry(@PathVariable UUID topicId, @PathVariable UUID entryId) {
        UUID userId = userService.getCurrentUserId();
        return reviewService.getReviewsForEntry(entryId, userId);
    }

    @GetMapping("/due")
    public List<ReviewResponse> getDueReviews(@PathVariable UUID topicId, @PathVariable UUID entryId) {
        UUID userId = userService.getCurrentUserId();
        return reviewService.getDueReviews(entryId, userId);
    }

    @PatchMapping("/{reviewId}")
    public ReviewResponse updateReview(@PathVariable UUID topicId, @PathVariable UUID reviewId, @PathVariable UUID entryId, @Valid @RequestBody UpdateReviewRequest request) {
        return  reviewService.updateReview(reviewId, entryId, request);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable UUID topicId, @PathVariable UUID reviewId, @PathVariable UUID entryId) {
        reviewService.deleteReview(reviewId, entryId);
    }
}
