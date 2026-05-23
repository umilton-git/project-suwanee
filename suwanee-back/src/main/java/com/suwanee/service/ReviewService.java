package com.suwanee.service;

import com.suwanee.dto.request.CreateEntryRequest;
import com.suwanee.model.entity.Entry;
import com.suwanee.model.entity.Review;
import com.suwanee.repository.EntryRepository;
import com.suwanee.repository.ReviewRepository;
import com.suwanee.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final EntryRepository entryRepository;

    public Review createReview(UUID entryId, UUID userId, String result) {
        Entry entry =  entryRepository.findByIdAndTopicUserId(entryId, userId)
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        Review review = new Review();
        review.setEntry(entry);
        review.setResult(result);

        return reviewRepository.save(review);
    }
    public List<Review> getReviewsForEntry(UUID topicId, UUID entryId) {

        entryRepository.findByIdAndTopicUserId(entryId, topicId)
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        return reviewRepository.findByEntryIdOrderByCreatedAtDesc(entryId);
    }

    public Review updateReview(UUID reviewId, UUID entryId, String result) {
       Review review = reviewRepository.findByIdAndEntryId(reviewId, entryId)
               .orElseThrow(() -> new RuntimeException("Review not found"));
       review.setResult(result);
        return reviewRepository.save(review);
    }

    public void deleteReview(UUID reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
