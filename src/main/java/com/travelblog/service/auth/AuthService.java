package com.travelblog.service.auth;

import com.travelblog.dto.CredentialsDTO;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    Integer TTL_MILLIS = 1000*60*60*24;
    String COOKIE_NAME = "SESSION_ID";

    boolean checkCredentials(CredentialsDTO credentialsDTO);

    String generateAuthToken(String email);

    boolean isAuthenticated(HttpServletRequest request);

}
