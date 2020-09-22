package com.mag.conduit.infrastructure.mybatis.repository;


import com.mag.conduit.application.dto.ArticleData;
import com.mag.conduit.core.article.Article;
import com.mag.conduit.core.article.ArticleRepository;
import com.mag.conduit.infrastructure.mybatis.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MyBatisArticleRepository implements ArticleRepository {
    @Autowired
    ArticleMapper articleMapper;

    private Optional<ArticleData> findByColumn(String columnName, Object value, UUID currentUserId) {
        return articleMapper.findByColumn(columnName, value, currentUserId);
    }

    public Optional<ArticleData> findById(UUID id, UUID currentUserId) {
        return articleMapper.findByColumn("id", id, currentUserId);
    };

    public Optional<ArticleData> findBySlug(String slug, UUID currentUserId) {
        return articleMapper.findByColumn("slug", slug, currentUserId);
    };

    public Boolean checkIfSlugExists(String slug) {
        return articleMapper.checkIfSlugExists(slug);
    };

    public UUID save(Article article) {
        return articleMapper.save(article);
    }
}
