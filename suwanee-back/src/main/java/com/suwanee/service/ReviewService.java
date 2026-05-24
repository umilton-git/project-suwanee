package com.suwanee.service;

import com.suwanee.model.entity.Entry;
import com.suwanee.model.entity.Review;
import com.suwanee.repository.EntryRepository;
import com.suwanee.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final EntryRepository entryRepository;

    public Review createReview(UUID entryId, UUID userId) {
        Entry entry =  entryRepository.findByIdAndTopicUserId(entryId, userId)
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        Review review = new Review();
        review.setEntry(entry);
        review.setDueAt(calculateDueAt(entry.getConfidence()));

        return reviewRepository.save(review);
    }
    public List<Review> getReviewsForEntry(UUID entryId, UUID userId) {
        entryRepository.findByIdAndTopicUserId(entryId, userId)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
        return reviewRepository.findByEntryIdOrderByCreatedAtDesc(entryId);
    }

    public List<Review> getDueReviews(UUID entryId, UUID userId) {
        entryRepository.findByIdAndTopicUserId(entryId, userId)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
        return reviewRepository.findByEntryIdAndDueAtBefore(entryId, LocalDateTime.now());
    }

    public Review updateReview(UUID reviewId, UUID entryId, String result, LocalDateTime dueAt) {
       Review review = reviewRepository.findByIdAndEntryId(reviewId, entryId)
               .orElseThrow(() -> new RuntimeException("Review not found"));
       review.setResult(result);
       review.setReviewedAt(LocalDateTime.now());
       review.setDueAt(calculateNextDueAt(result, review.getEntry().getConfidence()));
       return reviewRepository.save(review);
    }

    public void deleteReview(UUID reviewId, UUID entryId) {
        Review review = reviewRepository.findByIdAndEntryId(reviewId, entryId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        reviewRepository.delete(review);
    }

    private LocalDateTime calculateDueAt(int confidence) {
        int daysUntilReview = switch (confidence) {
            case 1 -> 1;
            case 2 -> 3;
            case 3 -> 7;
            case 4 -> 14;
            case 5 -> 30;
            default -> 7;
        };
        return LocalDateTime.now().plusDays(daysUntilReview);
    }

    private LocalDateTime calculateNextDueAt(String result, int confidence) {
        return switch (result) {
            case "passed" -> LocalDateTime.now().plusDays(confidence * 7);
            case "failed" -> LocalDateTime.now().plusDays(1);
            case "skipped" -> LocalDateTime.now().plusDays(3);
            default -> LocalDateTime.now().plusDays(7);
        };
    }
}
