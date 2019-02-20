package com.travelblog.controller.resources;

import com.travelblog.dto.posts.PostContentDTO;
import com.travelblog.dto.posts.PostContentsListDTO;
import com.travelblog.dto.posts.PostDTO;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@RequestMapping(value = "/api/post", produces = MediaType.APPLICATION_JSON_VALUE)
public interface PostsControllerResources {

    /**
     * Get posts list
     * @param tabId ID of the tab
     * @param start Start index position of posts
     * @param end End index position of posts
     * @return List of posts limited by start and end indexes for the given tabId
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Couldn't fetch posts")
    })
    @GetMapping(path = "/tab/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<PostDTO> getPosts(
            @PathVariable(value = "id", required = true) Long tabId,
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
            @RequestParam(value = "end", required = false, defaultValue = "5") Integer end);

    /**
     * Get single post
     * @param id ID of the post
     * @return post content of the given id
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Couldn't fetch the post")
    })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<PostContentDTO> getPost(
            @PathVariable(value = "id", required = true) Long id);

    /**
     * Creates new post
     * @return new post content
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Couldn't create the post")
    })
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<PostContentDTO> createPost(
            @RequestBody PostContentDTO postContentDTO,
            HttpServletRequest request);

    /**
     * Update post
     * @return updated post content
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Couldn't update the post")
    })
    @PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<PostContentDTO> updatePost(
            @RequestBody PostContentDTO postContentDTO,
            HttpServletRequest request);

    /**
     * Delete post
     * @return boolean
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Couldn't delete the post")
    })
    @DeleteMapping(path = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<Boolean> deletePost(
            @PathVariable Long postId,
            HttpServletRequest request);

    /**
     * Get recent posts
     * @return recent posts list
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Couldn't fetch recent posts")
    })
    @GetMapping(path = "/recent", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<PostContentsListDTO> getRecentPosts(
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
            @RequestParam(value = "end", required = false, defaultValue = "2") Integer end);

}
