package com.travelblog.mapper;

import com.travelblog.dto.MetaDTO;
import com.travelblog.dto.PostContentDTO;
import com.travelblog.dto.PostDTO;
import com.travelblog.model.Post;
import com.travelblog.model.Tab;
import com.travelblog.model.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class PostsMapper {

    public List<PostContentDTO> mapPostsIterableToPostsContentDTOS(Iterable<Post> postsIterable) {
        List<PostContentDTO> postContentDTOS = new ArrayList<>();
        for(Post post : postsIterable) {
            PostContentDTO postContentDTO = PostContentDTO.builder()
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

    public PostContentDTO mapPostToPostsContentDTO(Post post) {
        return PostContentDTO.builder()
                .tab(new Tab(post.getTab().getId(), post.getTab().getName()))
                .createdAt(post.getCreatedAt())
                .title(post.getTitle())
                .content(post.getContent())
                .tags(post.getTags().stream().map(t -> t.getName()).collect(Collectors.toList()))
                .build();
    }

    public Post mapPostContentDTOToPost(PostContentDTO postContentDTO) {
        return Post.builder()
                .tab(postContentDTO.getTab())
                .createdAt(new Date())
                .title(postContentDTO.getTitle())
                .content(postContentDTO.getContent())
                .tags(postContentDTO.getTags().stream().map(tag -> new Tag(null, null, tag)).collect(Collectors.toList()))
                .build();
    }

}
