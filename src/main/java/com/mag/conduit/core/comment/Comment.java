package com.mag.conduit.core.comment;

import com.mag.conduit.application.dto.ProfileData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Comment {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String body;
    private ProfileData author;
}
