package com.travelblog.service.auth;

import org.springframework.stereotype.Service;

@Service
public interface JwtService {

    Integer TTL_MILLIS = 1000*60*60*24;

    String generateAuthToken(String email);

}
