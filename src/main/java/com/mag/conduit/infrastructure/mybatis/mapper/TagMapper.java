package com.mag.conduit.infrastructure.mybatis.mapper;

import com.mag.conduit.core.tag.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;
import java.util.UUID;

@Mapper
public interface TagMapper {
    @Select("SELECT * FROM tag WHERE title = #{title}")
    Optional<Tag> findByTitle(String title);

    @Select("INSERT INTO tag(title) VALUES (#{title}) RETURNING id")
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    UUID save(Tag tag);
}
