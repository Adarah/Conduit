package com.mag.conduit.infrastructure.mybatis.mapper;

import com.mag.conduit.application.dto.ArticleData;
import com.mag.conduit.core.article.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;
import java.util.UUID;

@Mapper
public interface ArticleMapper {
    Optional<ArticleData> findByColumn(String columnName, Object value, UUID currentUserId);
    Optional<ArticleData> findById(UUID id);
    Optional<ArticleData> findBySlug(String slug);

    Boolean checkIfSlugExists(String slug);

    UUID save(Article article);
}
