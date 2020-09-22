package com.mag.conduit.infrastructure.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.UUID;

@Mapper
public interface UserFollowMapper {
    @Select("SELECT EXISTS(SELECT 1 FROM user_follow WHERE user_id = #{current} AND target_id = #{target})")
    boolean isCurrentFollowingTarget(UUID current, UUID target);
}
