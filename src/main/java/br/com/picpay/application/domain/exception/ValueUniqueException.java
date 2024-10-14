package br.com.picpay.application.domain.exception;

public class ValueUniqueException extends RuntimeException {
    public ValueUniqueException(String message) {
        super(message);
    }
}
