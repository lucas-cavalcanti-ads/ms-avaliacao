package com.lucavpa.msavaliacao.shared.web.handler;

import com.lucavpa.msavaliacao.review.domain.exception.ReviewNotFoundException;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LogManager.getLogger(ApiExceptionHandler.class);

    record AtributesError (String propertyPath, String messageTemplate){}

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<String> handlerReviewNotFound(
            ReviewNotFoundException exception, HttpServletRequest request) {

        Map<String, Object> body = Map.of(
            "title", "Recurso não encontrado",
            "status", 404,
            "detail", exception.getMessage(),
            "uri", request.getRequestURI()
        );

        logger.warn("[HANDLER][REVIEWS] Avaliação não encontrada " + body);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<AtributesError>> handlerConstraintViolation(
            final ConstraintViolationException exception, final HttpServletRequest request) {

        var errors = exception.getConstraintViolations().stream()
                        .map(indice -> new AtributesError(
                            indice.getPropertyPath().toString(),
                            indice.getMessageTemplate()))
                        .toList();

        Map<String, Object> body = Map.of(
                "title", "Requisição inválida",
                "status", 400,
                "detail", exception.getConstraintViolations(),
                "uri", request.getRequestURI()
        );

        logger.warn("[HANDLER][REVIEWS] Requisição inválida " + body);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}