package com.travelblog.service.auth.impl;

import com.travelblog.model.User;
import com.travelblog.model.redis.AuthToken;
import com.travelblog.repository.UserRepository;
import com.travelblog.repository.redis.AuthTokenRepository;
import com.travelblog.service.auth.AuthService;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;
import io.jsonwebtoken.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.*;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthTokenRepository authTokenRepository;

    public boolean checkEmailAndPassword(String email, String password) {
        Optional<User> optionalUser = userRepository.findOneByEmail(email);
        if (! optionalUser.isPresent()) {
            return false;
        }
        return checkPassword(password, optionalUser.get().getPassword());
    }

    private boolean checkPassword(String plainPassword, String hashPassword) {
        return BCrypt.checkpw(plainPassword, hashPassword);
    }

    public boolean isAuthenticated(HttpServletRequest request) {
        String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        Optional<AuthToken> authTokenOptional = authTokenRepository.findById(headerToken);
        if (! authTokenOptional.isPresent()) {
            return false;
        }

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Date exp = Jwts.parser().setSigningKey(key).parseClaimsJws(authTokenOptional.get().getToken()).getBody().getExpiration();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (now.getTime() > exp.getTime()) {
            return false;
        }
        return true;
    }

}
