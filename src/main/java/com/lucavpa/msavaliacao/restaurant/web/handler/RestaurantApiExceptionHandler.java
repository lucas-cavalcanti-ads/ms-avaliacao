package com.lucavpa.msavaliacao.restaurant.web.handler;

import com.lucavpa.msavaliacao.restaurant.domain.exception.RestaurantNotFoundException;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestaurantApiExceptionHandler {

    private static final Logger logger = LogManager.getLogger(RestaurantApiExceptionHandler.class);

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<String> handlerRestaurantNotFound (
            RestaurantNotFoundException exception, HttpServletRequest request) {

        Map<String, Object> body = Map.of(
                "title", "Recurso não encontrado",
                "status", 404,
                "detail", exception.getMessage(),
                "uri", request.getRequestURI()
        );

        logger.warn("[HANDLER][RESTAURANT] Nenhuma avaliação encontrada para o restaurante " + body);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}