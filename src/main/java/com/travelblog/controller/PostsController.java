package com.travelblog.controller;

import com.travelblog.controller.resources.PostsControllerResources;
import com.travelblog.dto.posts.*;
import com.travelblog.error.PostsError;
import com.travelblog.exception.AuthenticationException;
import com.travelblog.exception.AuthorizationException;
import com.travelblog.exception.PostsException;
import com.travelblog.mapper.PostsMapper;
import com.travelblog.model.Post;
import com.travelblog.repository.PostsRepository;
import com.travelblog.service.auth.AuthService;
import com.travelblog.service.PostsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class PostsController implements PostsControllerResources {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private PostsMapper postsMapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public CompletableFuture<PostDTO> getPosts(Long tabId, Integer start, Integer end) {
        if (end - start < 0) {
            throw new PostsException(new PostsError("End must be greater than start"));
        }

        Long totalForTab;
        try {
            totalForTab =  postsRepository.countByTabId(tabId);
        } catch (DataAccessException exception) {
            log.error(exception.toString());
            throw new PostsException(new PostsError("Couldn't fetch posts"));
        }

        if (totalForTab == 0) {
            return CompletableFuture.completedFuture(PostDTO.builder()
                    .meta(new MetaDTO())
                    .content(Collections.emptyList())
                    .build());
        }

        end = postsService.adjustEndParameter(end, totalForTab);

        MetaDTO metaDTO = MetaDTO.builder()
                .start(start)
                .end(end)
                .total(totalForTab)
                .build();

        Iterable<Post> posts = postsService.getChunkByTabId(tabId, start, end);
        List<PostContentDTO> postContentDTOS = postsMapper.mapToPostsContentDTOs(posts);

        return CompletableFuture.completedFuture(PostDTO.builder()
                .meta(metaDTO)
                .content(postContentDTOS)
                .build());
    }

    public CompletableFuture<PostContentDTO> getPost(Long id) {
        Optional<Post> postOptional;
        try {
            postOptional = postsRepository.findById(id);
        } catch (DataAccessException exception) {
            log.error(exception.toString());
            throw new PostsException(new PostsError("Couldn't fetch the post"));
        }

        PostContentDTO postContentDTO;
        if (postOptional.isPresent()) {
            postContentDTO = postsMapper.mapToPostContentDTO(postOptional.get());
        } else {
            throw new PostsException(new PostsError("Wrong ID"));
        }

        return CompletableFuture.completedFuture(postContentDTO);
    }

    public CompletableFuture<PostContentDTO> createPost(PostContentDTO postContentDTO, HttpServletRequest request) {
        if (! authService.isAuthenticated(request)) {
            throw new AuthorizationException();
        }
        Post post = postsMapper.mapToPost(postContentDTO);
        Post newPost;
        try {
            newPost = postsService.createPost(post);
        } catch (DataAccessException exception) {
            log.error(exception.toString());
            throw new PostsException(new PostsError("Couldn't create a post"));
        }
        PostContentDTO newPostContentDTO = postsMapper.mapToPostContentDTO(newPost);
        return CompletableFuture.completedFuture(newPostContentDTO);
    }

    public CompletableFuture<PostContentsListDTO> getRecentPosts(Integer start, Integer end) {
        Iterable<Post> posts;
        try {
            posts = postsService.getChunk(start, end);
        } catch (DataAccessException exception) {
            log.error(exception.toString());
            throw new PostsException(new PostsError("Couldn't fetch recent posts"));
        }

        PostContentsListDTO postContentsListDTO = postsMapper.mapToPostContentsListDTO(posts);
        return CompletableFuture.completedFuture(postContentsListDTO);
    }

}
