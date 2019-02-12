package com.travelblog.exception;

import com.travelblog.error.CommentsError;

public class CommentsException extends RuntimeException {

    public CommentsException(CommentsError commentsError) {
        super(commentsError.getErrorMessage());
    }

}
