package com.travelblog.controller;

import com.travelblog.dto.MetaDTO;
import com.travelblog.dto.PostContentDTO;
import com.travelblog.dto.PostDTO;
import com.travelblog.error.PostsError;
import com.travelblog.exception.PostsException;
import com.travelblog.mapper.PostsMapper;
import com.travelblog.mapper.SwiperMapper;
import com.travelblog.mapper.TabsMapper;
import com.travelblog.model.Post;
import com.travelblog.service.PostsService;
import com.travelblog.service.SwiperService;
import org.springframework.web.bind.annotation.*;

import com.travelblog.model.Tab;
import com.travelblog.service.TabsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@RestController
public class PostsController {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsMapper postsMapper;

    @GetMapping("/post/tab/{id}")
    public CompletableFuture<PostDTO> getPosts(
            @PathVariable(value = "id", required = true) Long tabId,
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
            @RequestParam(value = "end", required = false) Integer end) {
        Long total = postsService.getCount(tabId);
        if (total == 0) {
            return CompletableFuture.completedFuture(PostDTO.builder()
                    .meta(new MetaDTO(0, 0, total))
                    .content(Collections.emptyList())
                    .build());
        }
        if (end == null) {
            end = total.intValue() - 1;
        }
        if (end - start < 0) {
            throw new PostsException(new PostsError("End must be greater than start"));
        }
        if (end > total - 1) {
            end = total.intValue() - 1;
        }

        MetaDTO metaDTO = MetaDTO.builder()
                .start(start)
                .end(end)
                .total(total)
                .build();

        Iterable<Post> posts = postsService.getChunk(tabId, start, end);
        List<PostContentDTO> postContentDTOS = postsMapper.mapPostsIterableToPostsContentDTOS(posts);

        return CompletableFuture.completedFuture(PostDTO.builder()
                .meta(metaDTO)
                .content(postContentDTOS)
                .build());
    }

    @GetMapping("/post/{id}")
    public CompletableFuture<PostContentDTO> getPost(
            @PathVariable(value = "id", required = true) Long id) {
        Optional<Post> postOptional = postsService.getById(id);

        PostContentDTO postContentDTO;
        if (postOptional.isPresent()) {
            postContentDTO = postsMapper.mapPostToPostsContentDTO(postOptional.get());
        } else {
            throw new PostsException(new PostsError("Couldn't find post with specified ID"));
        }

        return CompletableFuture.completedFuture(postContentDTO);
    }

    @PostMapping("/post")
    public CompletableFuture<Boolean> createPost(
            @RequestBody PostContentDTO postContentDTO) {
        Post post = postsMapper.mapPostsContentDTOToPost(postContentDTO);
        postsService.createPost(post);
        return CompletableFuture.completedFuture(true);
    }

    @GetMapping("/recentpost")
    public CompletableFuture<List<PostContentDTO>> getRecentPosts() {
        Integer start = 0, end = 2;

        Iterable<Post> posts = postsService.getChunk(start, end);
        List<PostContentDTO> postContentDTOS = postsMapper.mapPostsIterableToPostsContentDTOS(posts);

        return CompletableFuture.completedFuture(postContentDTOS);
    }

}
