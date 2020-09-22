package com.mag.conduit.application;

import com.mag.conduit.application.dto.ProfileData;
import com.mag.conduit.core.userFollowRelation.UserFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileQueryService {
    @Autowired
    UserFollowRepository userFollowRepository;

    @Autowired
    UserQueryService userQueryService;


    @Transactional(readOnly = true)
    public Optional<ProfileData> findProfile(String username, UUID currentUserId) {
        return userQueryService
                .findByUsername(username)
                .map(fetchedUser -> {
                    boolean isFollowing = userFollowRepository
                            .isCurrentFollowingTarget(
                                    currentUserId, fetchedUser.getId()
                            );
                    return new ProfileData(fetchedUser, isFollowing);
                });
    }
}
