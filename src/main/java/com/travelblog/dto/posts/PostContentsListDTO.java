package com.travelblog.dto.posts;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostContentsListDTO {
    private List<PostContentDTO> postContents;
}
