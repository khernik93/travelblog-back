package com.travelblog.exception;

import com.travelblog.error.PostsError;

public class PostsException extends RuntimeException {

    public PostsException(PostsError postsError) {
        super(postsError.getErrorMessage());
    }

}
