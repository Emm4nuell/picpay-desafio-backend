package br.com.picpay.adapters.output.exception;

import br.com.picpay.adapters.output.sendmessage.SendLogService;
import br.com.picpay.application.domain.exception.AccountNotFoundException;
import br.com.picpay.application.domain.exception.NullValueException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalException {

    private final SendLogService sendLogService;

    @ExceptionHandler(NullValueException.class)
    public ResponseEntity<Map<String, Object>> handlerNullValueException(NullValueException exception, HttpServletRequest http){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getMessage(),
                http.getServletPath()));
    }

    @ExceptionHandler(ValueUniqueException.class)
    public ResponseEntity<Map<String , Object>> handlerValueUniqueException(ValueUniqueException exception, HttpServletRequest http){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                exception.getMessage(),
                http.getServletPath()));
    }

    @ExceptionHandler(ErrorGenericException.class)
    public ResponseEntity<Map<String , Object>> handlerErrorGenericException(ErrorGenericException exception, HttpServletRequest http){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                exception.getMessage(),
                http.getServletPath()));
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Map<String , Object>> handlerAccountNotFoundException(AccountNotFoundException exception, HttpServletRequest http){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
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

        sendLogService.send(config);

        return config;
    }
}
