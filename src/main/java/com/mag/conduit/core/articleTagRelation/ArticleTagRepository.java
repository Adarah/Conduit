package com.mag.conduit.core.articleTagRelation;

import java.util.List;
import java.util.UUID;

public interface ArticleTagRepository {
    void save(UUID articleId, List<UUID> tagUuids);
}
