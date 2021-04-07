package com.roman.user.auth.application.login;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Unauthorized error");
    }
}
