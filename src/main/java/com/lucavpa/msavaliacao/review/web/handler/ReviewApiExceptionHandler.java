package com.lucavpa.msavaliacao.review.web.handler;

import com.lucavpa.msavaliacao.review.domain.exception.ReviewNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ReviewApiExceptionHandler {

    private static final Logger logger = LogManager.getLogger(ReviewApiExceptionHandler.class);

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

}