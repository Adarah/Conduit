package com.mag.conduit.api.controller;

import com.mag.conduit.application.ArticleQueryService;
import com.mag.conduit.application.dto.ArticleData;
import com.mag.conduit.application.dto.form.ArticleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    ArticleQueryService articleQueryService;

    @PostMapping
    public ResponseEntity<?> writeArticle(@Valid @RequestBody ArticleForm articleForm,
                                          @AuthenticationPrincipal UUID currentUserId) {
        UUID articleId = articleQueryService.save(articleForm, currentUserId);
        ArticleData articleData = articleQueryService.findById(articleId, currentUserId).get();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(articleData);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> getArticle(@PathVariable String slug,
                                        @AuthenticationPrincipal UUID currentUserId) {
        System.out.println(currentUserId);
        return ResponseEntity.ok().build();
    }
}
