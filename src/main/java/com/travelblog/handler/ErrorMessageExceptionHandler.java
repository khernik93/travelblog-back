package com.travelblog.handler;

import com.travelblog.exception.AuthenticationException;
import com.travelblog.exception.PostsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class ErrorMessageExceptionHandler {

    @ExceptionHandler(PostsException.class)
    @RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity handlePricingException(final PostsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    @RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity handleAuthenticationException(final AuthenticationException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
