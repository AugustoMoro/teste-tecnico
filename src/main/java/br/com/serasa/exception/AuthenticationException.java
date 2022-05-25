package br.com.serasa.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String msg) {
        super(msg);
    }

    public AuthenticationException() {
        this(null);
    }
}
