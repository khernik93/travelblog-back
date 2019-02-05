package com.travelblog.service;

import com.travelblog.model.Post;

import java.util.Optional;

public interface PostsService {

    Integer adjustEndParameter(Integer end, Long total);

    Iterable<Post> getChunk(Integer start, Integer end);

    Iterable<Post> getChunkByTabId(Long tabId, Integer start, Integer end);

    Post createPost(Post post);

}
