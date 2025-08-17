package com.lucavpa.msavaliacao.review.web.handler;

import com.lucavpa.msavaliacao.review.domain.exception.ReviewNotFoundByIdException;

import java.util.Map;

import com.lucavpa.msavaliacao.review.domain.exception.ReviewNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ReviewApiExceptionHandler {

    private static final Logger logger = LogManager.getLogger(ReviewApiExceptionHandler.class);

    @ExceptionHandler(ReviewNotFoundByIdException.class)
    public ResponseEntity<String> handlerReviewNotByIdFound(
            ReviewNotFoundByIdException exception, HttpServletRequest request) {

        Map<String, Object> body = Map.of(
            "title", "Recurso não encontrado",
            "status", 404,
            "detail", exception.getMessage(),
            "uri", request.getRequestURI()
        );

        logger.warn("[HANDLER][REVIEWS] Avaliação não encontrada por ID" + body);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<String> handlerReviewNotFound(
            ReviewNotFoundException exception, HttpServletRequest request) {

        Map<String, Object> body = Map.of(
                "title", "Recurso não encontrado",
                "status", 404,
                "detail", exception.getMessage(),
                "uri", request.getRequestURI()
        );

        logger.warn("[HANDLER][REVIEWS] Nenhuma avaliação encontrada" + body);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}