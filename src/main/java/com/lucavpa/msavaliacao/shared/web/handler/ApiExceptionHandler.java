package com.lucavpa.msavaliacao.shared.web.handler;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LogManager.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String,String>>> handleBodyErrors(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> Map.of(
                        "propriedade", e.getField(),
                        "mensagem", e.getDefaultMessage()))
                .toList();

        Map<String, Object> body = Map.of(
                "title", "Requisição inválida",
                "status", 400,
                "detail", errors,
                "messagw", ex.getBindingResult()
        );

        logger.warn("[HANDLER][handleBodyErrors] Requisição inválida " + body);

        return ResponseEntity.badRequest().body(errors);
    }

}