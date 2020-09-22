package com.mag.conduit.api.exception;

import lombok.Data;

@Data
public class FieldErrorResource {
    final private String resource;
    final private String field;
    final private String code;
    final private String message;
}