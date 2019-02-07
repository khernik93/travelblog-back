package com.travelblog.service.auth;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface JwtService {

    String secretKey = "";

    String algorithm = "";

    String generateAuthToken(String email);

    Date getExpirationDate(String token);

}
