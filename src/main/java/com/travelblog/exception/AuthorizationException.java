package com.travelblog.exception;

public class AuthorizationException extends RuntimeException {

    public AuthorizationException() { super("Invalid authentication token"); }

}
