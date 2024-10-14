package br.com.picpay.application.domain.exception;

public class NullValueException extends RuntimeException {
    public NullValueException(String message) {
        super(message);
    }
}
