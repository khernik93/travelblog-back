package com.travelblog.dto.posts;

import com.travelblog.model.Tab;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class PostContentDTO {

    private Tab tab;
    private Date createdAt;
    private String title;
    private String content;
    private List<String> tags;

}
