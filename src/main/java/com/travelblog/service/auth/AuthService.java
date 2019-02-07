package com.travelblog.service.auth;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface AuthService {

    Integer TTL_MILLIS = 1000*60*60*24;

    String COOKIE_NAME = "SESSION_ID";

    boolean checkEmailAndPassword(String email, String password);

    boolean isAuthenticated(HttpServletRequest request);

}
