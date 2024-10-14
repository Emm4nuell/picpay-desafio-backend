package br.com.picpay.application.domain.exception;

public class ErrorGenericException extends RuntimeException {
    public ErrorGenericException(String message) {
        super(message);
    }
}
