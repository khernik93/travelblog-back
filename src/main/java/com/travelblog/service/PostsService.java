package com.travelblog.service;

import com.travelblog.dto.PostContentDTO;
import com.travelblog.model.Post;

import java.util.Optional;

public interface PostsService {

    Long getCount(Long tabId);

    Iterable<Post> getChunk(Integer start, Integer end);

    Iterable<Post> getChunk(Long tabId, Integer start, Integer end);

    Optional<Post> getById(Long id);

    void createPost(Post post);

}
