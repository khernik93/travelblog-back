package com.travelblog.service.auth.impl;

import com.travelblog.service.auth.AuthService;
import com.travelblog.service.auth.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Value("${auth.secretKey}")
    private String secretKey;

    @Value("${auth.algorithm}")
    private String algorithm;

    @Autowired
    private AuthService authService;

    public String generateAuthToken(String email) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] encodedSecretKey = decode(secretKey);
        SecretKey key = new SecretKeySpec(encodedSecretKey, algorithm);

        JwtBuilder builder = Jwts.builder()
                .setId(email)
                .setIssuedAt(now)
                .signWith(key);

        long expMillis = nowMillis + authService.TTL_MILLIS;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        return builder.compact();
    }

    public Date getExpirationDate(String token) {
        byte[] encodedSecretKey = decode(secretKey);
        return Jwts.parser()
                .setSigningKey(encodedSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    private byte[] decode(String string) {
        return Base64.getDecoder().decode(string);
    }

}
