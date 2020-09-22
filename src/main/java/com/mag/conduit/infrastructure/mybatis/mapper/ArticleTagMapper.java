package com.mag.conduit.infrastructure.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ArticleTagMapper {
    // Couldn't do this using annotations, so I used an xml file instead.
    // You can find it under resources/mapper
    void save(UUID articleId, List<UUID> tagUuids);
}
