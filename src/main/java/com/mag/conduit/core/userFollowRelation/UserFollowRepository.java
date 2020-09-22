package com.mag.conduit.core.userFollowRelation;

import java.util.UUID;

public interface UserFollowRepository {
    boolean isCurrentFollowingTarget(UUID current, UUID target);
}
