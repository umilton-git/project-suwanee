package com.suwanee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suwanee.model.entity.Review;
import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

    List<Review> findByEntryIdOrderByCreatedAtDesc(UUID entryId);

    Optional<Review> findByIdAndEntryId(UUID id, UUID entryId);

    long countByEntryId(UUID entryId);

    boolean existsByIdAndEntryId(UUID id, UUID entryId);

    List<Review> findByEntryIdAndDueAtBefore(UUID entryId, LocalDateTime now);
}