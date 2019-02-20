package com.travelblog.controller;

import com.travelblog.controller.resources.PostsControllerResources;
import com.travelblog.controller.resources.SystemControllerResources;
import com.travelblog.dto.posts.*;
import com.travelblog.error.PostsError;
import com.travelblog.exception.AuthenticationException;
import com.travelblog.exception.AuthorizationException;
import com.travelblog.exception.PostsException;
import com.travelblog.mapper.PostsMapper;
import com.travelblog.model.Post;
import com.travelblog.repository.PostsRepository;
import com.travelblog.service.StorageService;
import com.travelblog.service.auth.AuthService;
import com.travelblog.service.PostsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class SystemController implements SystemControllerResources {

    @Autowired
    private StorageService storageService;

    public CompletableFuture<String> uploadPhoto(MultipartFile file, HttpServletRequest request) {
        String path = storageService.store(file);
        return CompletableFuture.completedFuture("resources/" + path);
    }

}
