package com.mag.conduit.core.tag;

import java.util.Optional;
import java.util.UUID;

public interface TagRepository {
    Optional<Tag> findByTitle(String title);
    UUID save(Tag tag);
}
