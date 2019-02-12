package com.travelblog.dto.comments;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommentsListDTO {
    private List<CommentDTO> comments;
}
