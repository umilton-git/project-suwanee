package com.suwanee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suwanee.model.entity.Entry;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<Entry, UUID> {

    List<Entry> findByTopicIdOrderByCreatedAtDesc(UUID topicId);

    Optional<Entry> findByIdAndTopicId(UUID id, UUID topicId);

    long countByTopicId(UUID topicId);

    boolean existsByIdAndTopicId(UUID id, UUID topicId);
}