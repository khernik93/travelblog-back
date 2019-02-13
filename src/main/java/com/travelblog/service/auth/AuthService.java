package com.travelblog.service.auth;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface AuthService {

    /**
     * Time until jwt token expires
     */
    Integer TTL_MILLIS = 1000*60*60*24;

    /**
     * Name of the cookie holding jwt token
     */
    String COOKIE_NAME = "SESSION_ID";

    /**
     * Check if email is in the database, and then check if its hash matches the plain password
     * @param email
     * @param password
     * @return
     */
    boolean checkEmailAndPassword(String email, String password);

    /**
     * Fetches authorization token from headers and checks if it's valid
     * @param request
     * @return
     */
    boolean isAuthenticated(HttpServletRequest request);

}
