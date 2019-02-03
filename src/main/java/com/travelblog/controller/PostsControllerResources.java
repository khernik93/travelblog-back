package com.travelblog.controller;

import com.travelblog.dto.PostContentDTO;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@RequestMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
public interface PostsControllerResources {

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Couldn't add new post")
    })
    CompletableFuture<Boolean> createPost(
            @PathVariable(value = "id", required = true) Long id,
            @RequestBody PostContentDTO postContentDTO);

}
