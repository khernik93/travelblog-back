package com.travelblog.service.auth.impl;

import com.travelblog.model.User;
import com.travelblog.model.redis.AuthToken;
import com.travelblog.repository.UserRepository;
import com.travelblog.repository.redis.AuthTokenRepository;
import com.travelblog.service.auth.AuthService;
import com.travelblog.service.auth.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCrypt bCrypt;

    public boolean checkEmailAndPassword(String email, String password) {
        Optional<User> optionalUser = userRepository.findOneByEmail(email);
        if (! optionalUser.isPresent()) {
            return false;
        }
        return checkPassword(password, optionalUser.get().getPassword());
    }

    private boolean checkPassword(String plainPassword, String hashPassword) {
        return bCrypt.checkpw(plainPassword, hashPassword);
    }

    public boolean isAuthenticated(HttpServletRequest request) {
        String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        Optional<AuthToken> authTokenOptional = authTokenRepository.findById(headerToken);
        if (! authTokenOptional.isPresent()) {
            return false;
        }
        boolean isValid;
        try {
            isValid = isTokenValid(authTokenOptional.get().getToken());
        } catch (ExpiredJwtException exception) {
            log.error(exception.getMessage());
            isValid = false;
        }
        return isValid;
    }

    private boolean isTokenValid(String token) {
        Date expirationDate = jwtService.getExpirationDate(token);
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        return (now.getTime() <= expirationDate.getTime());
    }

}
