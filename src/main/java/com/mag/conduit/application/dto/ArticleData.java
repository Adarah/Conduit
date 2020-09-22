package com.mag.conduit.application.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.mag.conduit.core.tag.Tag;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@JsonRootName("article")
public class ArticleData {
    private String slug;
    private String title;
    private String description;
    private String body;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private List<Tag> tagList;
    private boolean favorited;
    @Unsigned
    private int favoritesCount;
    private ProfileData author;
}
