package com.travelblog.exception;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() { super("Invalid authentication token"); }

}
