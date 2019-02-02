package com.travelblog.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostDTO {

    private MetaDTO meta;
    private List<PostContentDTO> content;

}
