package com.mag.conduit.core.article;

import com.github.slugify.Slugify;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Article {
    private UUID id;
    private String slug;
    private String title;
    private String description;
    private String body;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID authorId;

    public Article(String title, String slug, String description, String body, UUID authorId) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.authorId = authorId;
    }

    public static String toSlug(String title) {
        Slugify slugifier = new Slugify();
        return slugifier.slugify(title);
    }
}
