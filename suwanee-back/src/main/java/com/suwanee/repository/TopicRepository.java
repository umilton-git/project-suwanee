package com.suwanee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suwanee.model.entity.Topic;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {
    List<Topic> findByUserId(UUID userId);

    Optional<Topic> findByIdAndUserId(UUID id, UUID userId);

    long countByUserId(UUID userId);

    boolean existsByIdAndUserId(UUID id, UUID userId);
}