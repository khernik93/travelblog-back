package com.travelblog.dto.comments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String name;
    private Date createdAt;
    private String content;
    private String email;

    public String getName() {
        return name != null ? name : "Anonymous";
    }
}
