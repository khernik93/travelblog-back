package com.travelblog.service.auth.impl;

import com.travelblog.dto.CredentialsDTO;
import com.travelblog.model.redis.AuthToken;
import com.travelblog.model.User;
import com.travelblog.repository.redis.AuthTokenRepository;
import com.travelblog.repository.UserRepository;
import com.travelblog.service.auth.AuthService;
import com.travelblog.service.auth.AuthTokenService;
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
    private AuthTokenService authTokenService;

    public boolean checkCredentials(CredentialsDTO credentialsDTO) {
        String providedPassword = credentialsDTO.getPassword();
        Optional<User> optionalUser = userRepository.findOneByEmail(credentialsDTO.getEmail());
        if (! optionalUser.isPresent()) {
            return false;
        }
        return checkPassword(providedPassword, optionalUser.get().getPassword());
    }

    private boolean checkPassword(String plainPassword, String hashPassword) {
        return BCrypt.checkpw(plainPassword, hashPassword);
    }

    public String generateAuthToken(String email) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        JwtBuilder builder = Jwts.builder()
                .setId(email)
                .setIssuedAt(now)
                .signWith(key);

        if (TTL_MILLIS >= 0) {
            long expMillis = nowMillis + TTL_MILLIS;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public boolean isAuthenticated(HttpServletRequest request) {
        String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        String sessionToken = authTokenService.getAuthToken(headerToken).getToken();
        if (sessionToken == null) {
            return false;
        }
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Date exp = Jwts.parser().setSigningKey(key).parseClaimsJws(sessionToken).getBody().getExpiration();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (now.getTime() > exp.getTime()) {
            return false;
        }
        return true;
    }

}
