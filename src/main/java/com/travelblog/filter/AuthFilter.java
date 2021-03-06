package com.travelblog.filter;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private String principalRequestHeader;

    public AuthFilter(String principalRequestHeader) {
        this.principalRequestHeader = principalRequestHeader;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(principalRequestHeader);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }

}
