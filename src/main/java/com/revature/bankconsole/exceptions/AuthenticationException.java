package com.revature.bankconsole.exceptions;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException() {
        super("Failed to authenticate");
    }
}

