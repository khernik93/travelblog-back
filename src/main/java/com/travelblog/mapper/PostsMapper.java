package com.travelblog.mapper;

import com.travelblog.dto.posts.PostContentDTO;
import com.travelblog.dto.posts.PostContentsListDTO;
import com.travelblog.model.Post;
import com.travelblog.model.Tab;
import com.travelblog.model.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostsMapper {

    public List<PostContentDTO> mapToPostsContentDTOs(Iterable<Post> postsIterable) {
        List<PostContentDTO> postContentDTOS = new ArrayList<>();
        for(Post post : postsIterable) {
            PostContentDTO postContentDTO = PostContentDTO.builder()
                    .id(post.getId())
                    .tab(new Tab(post.getTab().getId(), post.getTab().getName()))
                    .createdAt(post.getCreatedAt())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .tags(post.getTags().stream().map(t -> t.getName()).collect(Collectors.toList()))
                    .build();
            postContentDTOS.add(postContentDTO);
        }
        return postContentDTOS;
    }

    public PostContentDTO mapToPostContentDTO(Post post) {
        return PostContentDTO.builder()
                .id(post.getId())
                .tab(new Tab(post.getTab().getId(), post.getTab().getName()))
                .createdAt(post.getCreatedAt())
                .title(post.getTitle())
                .content(post.getContent())
                .tags(post.getTags().stream().map(t -> t.getName()).collect(Collectors.toList()))
                .build();
    }

    public Post mapToPost(PostContentDTO postContentDTO) {
        return Post.builder()
                .tab(postContentDTO.getTab())
                .title(postContentDTO.getTitle())
                .content(postContentDTO.getContent())
                .createdAt(new Date())
                .tags(postContentDTO.getTags().stream().map(name -> Tag.builder().name(name).build()).collect(Collectors.toList()))
                .build();
    }

    public PostContentsListDTO mapToPostContentsListDTO(Iterable<Post> postsIterable) {
        List<PostContentDTO> postContentDTOs = new ArrayList<>();
        for (Post post : postsIterable) {
            postContentDTOs.add(mapToPostContentDTO(post));
        }
        return PostContentsListDTO.builder()
                .postContents(postContentDTOs)
                .build();
    }

}
