package com.travelblog.service;

import com.travelblog.model.Post;

public interface PostsService {

    /**
     * Adjusts end query parameter to the current DB situation - the adjusted value will be returned in Meta later on
     * @param end Provided end parameter
     * @param total Total entries in the database
     * @return Adjusted end parameter
     */
    Integer adjustEndParameter(Integer end, Long total);

    /**
     * Get chunk of posts
     * @param start 0-indexed
     * @param end 0-indexed
     * @return iterable posts
     */
    Iterable<Post> getChunk(Integer start, Integer end);

    /**
     * Get chunk of posts for specific tab
     * @param tabId ID of the tab
     * @param start 0-indexed
     * @param end 0-indexed
     * @return iterable posts
     */
    Iterable<Post> getChunkByTabId(Long tabId, Integer start, Integer end);

    /**
     * Create a new post or update an existing post
     * @param post
     * @return new post
     */
    Post updatePost(Post post);

}
