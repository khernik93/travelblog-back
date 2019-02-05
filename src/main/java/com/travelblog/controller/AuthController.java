package com.travelblog.controller;

import com.travelblog.dto.CredentialsDTO;
import com.travelblog.model.redis.AuthToken;
import com.travelblog.service.auth.AuthService;
import com.travelblog.service.auth.AuthTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthTokenService authTokenService;

    @PostMapping("/signIn")
    public CompletableFuture<Boolean> signIn(
            @RequestBody CredentialsDTO credentialsDTO,
            HttpServletRequest request,
            HttpServletResponse response) {
        Boolean doCredentialsMatch = authService.checkCredentials(credentialsDTO);

        if (doCredentialsMatch) {
            String email = credentialsDTO.getEmail();
            AuthToken authToken = AuthToken.builder()
                    .token(authService.generateAuthToken(email))
                    .build();
            authTokenService.setAuthToken(authToken);

            Cookie cookie = new Cookie(authService.COOKIE_NAME, authToken.getToken());
            cookie.setMaxAge(authService.TTL_MILLIS);
            response.addCookie(cookie);
        }

        return CompletableFuture.completedFuture(doCredentialsMatch);
    }

}
