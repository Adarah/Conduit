package com.mag.conduit.core.article;

import com.mag.conduit.application.dto.ArticleData;

import java.util.Optional;
import java.util.UUID;

public interface ArticleRepository {
    Optional<ArticleData> findById(UUID id, UUID currentUserId);
    Optional<ArticleData> findBySlug(String slug, UUID currentUserId);
    Boolean checkIfSlugExists(String slug);

    UUID save(Article article);
}
