package com.travelblog.dto.comments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String content;
}
