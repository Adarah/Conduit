package com.mag.conduit.infrastructure.mybatis.repository;

import com.mag.conduit.core.userFollowRelation.UserFollowRepository;
import com.mag.conduit.infrastructure.mybatis.mapper.UserFollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class MyBatisUserFollowRepository implements UserFollowRepository {
    @Autowired
    UserFollowMapper userFollowMapper;
    @Override
    public boolean isCurrentFollowingTarget(UUID current, UUID target) {
        return userFollowMapper.isCurrentFollowingTarget(current, target);
    }
}
