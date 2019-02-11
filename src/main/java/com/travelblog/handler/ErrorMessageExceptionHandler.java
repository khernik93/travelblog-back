package com.travelblog.handler;

import com.travelblog.exception.AuthenticationException;
import com.travelblog.exception.AuthorizationException;
import com.travelblog.exception.PostsException;
import com.travelblog.exception.TabsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@Slf4j
public class ErrorMessageExceptionHandler {

    @ExceptionHandler(TabsException.class)
    @RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity handleTabsException(final TabsException exception) {
        log.error(exception.toString());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PostsException.class)
    @RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity handlePostsException(final PostsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthorizationException.class)
    @RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity handleAuthorizationException(final AuthorizationException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    @RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity handleAuthenticationException(final AuthenticationException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
