package com.mag.conduit.application.dto.form;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@JsonRootName("article")
@Getter
public class ArticleForm {
    @NotBlank(message = "{title.not_blank}")
    private String title;
    @NotBlank(message = "{description.not_blank")
    private String description;
    @NotBlank(message = "{body.not_blank}")
    private String body;
    // String instead of Tag, because we need to hit the DB to get Tag objects since there's
    // no way for the user to provide us with the proper Tag ids
    private List<String> tagList;
}
