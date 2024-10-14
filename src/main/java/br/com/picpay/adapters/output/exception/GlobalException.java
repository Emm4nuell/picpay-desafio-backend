package br.com.picpay.adapters.output.exception;

import br.com.picpay.application.domain.exception.NullValueException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(NullValueException.class)
    public ResponseEntity<Map<String, Object>> handlerNullValueException(NullValueException exception, HttpServletRequest http){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getMessage(),
                http.getServletPath()));
    }

    private Map<String, Object> buildErrorResponse(int status, String error, String message, String path){
        log.error("Message: {} ; Error: {} ; Path: {}", message, error, path);

        Map<String, Object> config = new HashMap<>();
        config.put("timestamp", LocalDateTime.now());
        config.put("status", status);
        config.put("message", message);
        config.put("error", error);
        config.put("path", path);
        return config;
    }
}
