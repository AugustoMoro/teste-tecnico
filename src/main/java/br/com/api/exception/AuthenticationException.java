package br.com.api.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String msg) {
        super(msg);
    }

    public AuthenticationException() {
        this(null);
    }
}
