package com.travelblog.controller.resources;

import com.travelblog.dto.comments.CommentDTO;
import com.travelblog.dto.comments.CommentsListDTO;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RequestMapping(value = "/comment", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CommentsControllerResources {

    /**
     * Get comments list
     * @param postId ID of the post
     * @return List of comments for a given post
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Couldn't fetch comments")
    })
    @GetMapping(path = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<CommentsListDTO> getComments(
            @PathVariable(value = "postId", required = true) Long postId);

    /**
     * Adds new comment
     * @return new comment
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Couldn't add the comment")
    })
    @PostMapping(path = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<CommentDTO> addComment(
            @RequestBody CommentDTO commentDTO,
            @PathVariable(value = "postId", required = true) Long postId);

}
