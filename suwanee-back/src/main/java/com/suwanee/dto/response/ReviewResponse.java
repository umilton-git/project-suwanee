package com.suwanee.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;
import com.suwanee.model.entity.Review;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponse {
    private UUID id;
    private UUID entryId;
    private LocalDateTime reviewedAt;
    private LocalDateTime createdAt;
    private LocalDateTime dueAt;
    private String result;

    public static ReviewResponse from(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.id = review.getId();
        response.entryId = review.getEntry().getId();
        response.result = review.getResult();
        response.reviewedAt = review.getReviewedAt();
        response.dueAt = review.getDueAt();
        response.createdAt = review.getCreatedAt();
        return response;
    }
}
