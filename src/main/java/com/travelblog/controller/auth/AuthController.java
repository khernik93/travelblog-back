package com.travelblog.controller.auth;

import com.travelblog.controller.auth.resources.AuthControllerResources;
import com.travelblog.dto.auth.CredentialsDTO;
import com.travelblog.exception.AuthenticationException;
import com.travelblog.model.redis.AuthToken;
import com.travelblog.repository.redis.AuthTokenRepository;
import com.travelblog.service.auth.AuthService;
import com.travelblog.service.auth.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class AuthController implements AuthControllerResources {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthTokenRepository authTokenRepository;

    public CompletableFuture<String> signIn(
            CredentialsDTO credentialsDTO,
            HttpServletRequest request,
            HttpServletResponse response) {
        String email = credentialsDTO.getEmail();
        String password = credentialsDTO.getPassword();
        Boolean doCredentialsMatch = authService.checkEmailAndPassword(email, password);

        if (! doCredentialsMatch) {
            throw new AuthenticationException();
        }

        String token = jwtService.generateAuthToken(email);
        authTokenRepository.save(new AuthToken(token));

        Cookie cookie = new Cookie(authService.COOKIE_NAME, token);
        cookie.setMaxAge(authService.TTL_MILLIS/1000);
        cookie.setPath("/");
        response.addCookie(cookie);

        return CompletableFuture.completedFuture("");
    }

}
