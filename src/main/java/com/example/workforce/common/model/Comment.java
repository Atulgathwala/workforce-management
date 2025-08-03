package com.example.workforce.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
    private String author;
    private String text;
    private Long timestamp;
}
