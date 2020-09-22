package com.mag.conduit.application;

import com.mag.conduit.application.dto.ArticleData;
import com.mag.conduit.application.dto.form.ArticleForm;
import com.mag.conduit.core.article.Article;
import com.mag.conduit.core.article.ArticleRepository;
import com.mag.conduit.core.articleTagRelation.ArticleTagRepository;
import com.mag.conduit.core.tag.Tag;
import com.mag.conduit.core.tag.TagRepository;
import com.mag.conduit.core.userFollowRelation.UserFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArticleQueryService {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    ArticleTagRepository articleTagRepository;

    @Autowired
    UserFollowRepository userFollowRepository;

    @Transactional(readOnly = true)
    public Optional<ArticleData> findById(UUID id, UUID currentUserId) {
        Optional<ArticleData> maybeArticleData = articleRepository.findById(id, currentUserId);
        maybeArticleData.ifPresent(articleData -> {
            if (articleData.getTagList() == null) {
                articleData.setTagList(Collections.emptyList());
            }
        });
//         if(maybeArticleData.isPresent()) {
//             articleTagRepository.find
//         }
        return maybeArticleData;
    }

    @Transactional(readOnly = true)
    public Optional<ArticleData> findBySlug(String slug, UUID currentUserId) {
        return articleRepository.findBySlug(slug, currentUserId);
    }

    @Transactional
    public UUID save(ArticleForm articleForm, UUID authorId) {
        String slugCandidate = Article.toSlug(articleForm.getTitle());
        boolean slugTaken = articleRepository.checkIfSlugExists(slugCandidate);
        if (slugTaken) {
            slugCandidate = generateValidSlug(slugCandidate);
        }
        Article article = new Article(
                articleForm.getTitle(),
                slugCandidate,
                articleForm.getDescription(),
                articleForm.getBody(),
                authorId
        );
        UUID articleId = articleRepository.save(article);
        if (articleForm.getTagList() != null) {
            List<Tag> tagList = articleForm.getTagList().stream()
                    .map(this::createOrGetTagFromTitle)
                    .collect(Collectors.toList());
            List<UUID> tagUuids = tagList.stream().map(Tag::getId).collect(Collectors.toList());
            articleTagRepository.save(articleId, tagUuids);
        }
        return articleId;
    }

    private String generateValidSlug(String slugBase) {
        String slug;
        do {
            String suffix = UUID.randomUUID().toString().substring(24);
            slug = slugBase + '-' + suffix;
        } while (articleRepository.checkIfSlugExists(slug));
        return slug;
    }

    private Tag createOrGetTagFromTitle(String title) {
        return tagRepository.findByTitle(title).orElseGet(() -> {
            Tag tag = new Tag(title);
            tag.setId(tagRepository.save(tag));
            return tag;
        });
    }
}
