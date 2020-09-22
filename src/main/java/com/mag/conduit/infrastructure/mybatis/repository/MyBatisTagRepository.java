package com.mag.conduit.infrastructure.mybatis.repository;

import com.mag.conduit.core.tag.Tag;
import com.mag.conduit.core.tag.TagRepository;
import com.mag.conduit.infrastructure.mybatis.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MyBatisTagRepository implements TagRepository {
    @Autowired
    TagMapper tagMapper;

    public Optional<Tag> findByTitle(String title) {
        return tagMapper.findByTitle(title);
    }

    public UUID save(Tag tag) {
        return tagMapper.save(tag);
    }
}
