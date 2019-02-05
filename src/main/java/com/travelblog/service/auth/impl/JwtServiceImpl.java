package com.travelblog.service.auth.impl;

import com.travelblog.service.auth.JwtService;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;

import java.security.Key;
import java.util.*;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

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

}
