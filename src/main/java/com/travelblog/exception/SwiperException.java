package com.travelblog.exception;

import com.travelblog.error.SwiperError;

public class SwiperException extends RuntimeException {

    public SwiperException(SwiperError swiperError) {
        super(swiperError.getErrorMessage());
    }

}
