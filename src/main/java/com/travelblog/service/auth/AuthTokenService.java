package com.travelblog.service.auth;

import com.travelblog.model.redis.AuthToken;
import org.springframework.stereotype.Service;

@Service
public interface AuthTokenService {

    void setAuthToken(AuthToken authToken);

    AuthToken getAuthToken(String email);

}
