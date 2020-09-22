package com.mag.conduit.infrastructure.mybatis.repository;

import com.mag.conduit.core.articleTagRelation.ArticleTagRepository;
import com.mag.conduit.infrastructure.mybatis.mapper.ArticleTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class MyBatisArticleTagRepository implements ArticleTagRepository {
    @Autowired
    ArticleTagMapper articleTagMapper;

    public void save(UUID articleId, List<UUID> tagUuids) {
        articleTagMapper.save(articleId, tagUuids);
    }

}
