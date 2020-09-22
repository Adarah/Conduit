package com.mag.conduit.core.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @JsonIgnore
    private UUID id;
    private String title;

    public Tag(String title) {
        this.title = title;
    }
}
