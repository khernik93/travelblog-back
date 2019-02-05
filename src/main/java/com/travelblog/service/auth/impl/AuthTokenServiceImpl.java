package com.travelblog.service.auth.impl;

import com.travelblog.model.redis.AuthToken;
import com.travelblog.repository.redis.AuthTokenRepository;
import com.travelblog.service.auth.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthTokenServiceImpl implements AuthTokenService {

    @Autowired
    private AuthTokenRepository authTokenRepository;

    public void setAuthToken(AuthToken authToken) {
        authTokenRepository.save(authToken);
    }

    public AuthToken getAuthToken(String token) {
        Optional<AuthToken> optionalAuthToken = authTokenRepository.findById(token);
        if (! optionalAuthToken.isPresent()) {
            return null;
        }
        return optionalAuthToken.get();
    }

}
