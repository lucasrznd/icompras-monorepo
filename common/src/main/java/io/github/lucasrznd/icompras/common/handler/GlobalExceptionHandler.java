package io.github.lucasrznd.icompras.common.handler;

import io.github.lucasrznd.icompras.common.exception.BusinessException;
import io.github.lucasrznd.icompras.common.exception.InvalidApiKeyException;
import io.github.lucasrznd.icompras.common.exception.ResourceNotFoundException;
import io.github.lucasrznd.icompras.common.exception.StandardError;
import io.github.lucasrznd.icompras.common.exception.StorageException;
import io.github.lucasrznd.icompras.common.exception.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> handleNotReadable(HttpServletRequest request, Locale locale) {
        String message = messageSource.getMessage("invalid.request", null, "Requisição inválida ou mal formatada", locale);
        return buildResponse(HttpStatus.BAD_REQUEST, message, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request, Locale locale) {
        String message = messageSource.getMessage("validation.error", null, "Erro na validação de atributos", locale);
        var error = ValidationException.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(message)
                .errors(new ArrayList<>())
                .path(request.getRequestURI()).build();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request, Locale locale) {
        String message = resolveMessage(ex, locale);
        return buildResponse(HttpStatus.NOT_FOUND, message, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> handleBusiness(BusinessException ex, HttpServletRequest request, Locale locale) {
        String message = resolveMessage(ex, locale);
        return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, message, request);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<StandardError> handleMissingRequestHeader(MissingRequestHeaderException ex, HttpServletRequest request, Locale locale) {
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    @ExceptionHandler(InvalidApiKeyException.class)
    public ResponseEntity<StandardError> handleMissingRequestHeader(InvalidApiKeyException ex, HttpServletRequest request, Locale locale) {
        String message = resolveMessage(ex, locale);
        return buildResponse(HttpStatus.UNAUTHORIZED, message, request);
    }

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<StandardError> handleStorage(StorageException ex, HttpServletRequest request, Locale locale) {
        String message = resolveMessage(ex, locale);
        return buildResponse(HttpStatus.NOT_FOUND, message, request);
    }

    private String resolveMessage(BusinessException ex, Locale locale) {
        return messageSource.getMessage(ex.getMessageKey(), ex.getArgs(), ex.getMessageKey(), locale);
    }

    private ResponseEntity<StandardError> buildResponse(HttpStatus status, String message, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(error);
    }
}
