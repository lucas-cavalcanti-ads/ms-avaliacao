package com.lucavpa.msavaliacao.review.web.controller;

import com.lucavpa.msavaliacao.review.domain.model.Review;
import com.lucavpa.msavaliacao.review.app.service.ReviewService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    @Autowired
    ReviewService reviewService;

    private static final Logger logger = LogManager.getLogger(ReviewsController.class);

    @GetMapping
    public ResponseEntity<List<Review>> getAll () {
        logger.info("[CONTROLLER][REVIEWS] Iniciando Consulta de todas as Avaliações.");
        return new ResponseEntity<>(reviewService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Review> getById (@PathVariable Long id) {
        logger.info("[CONTROLLER][REVIEWS] Iniciando Consulta de uma Avaliação specífica.");
        return new ResponseEntity<>(reviewService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <Review> save (@RequestBody Review review) {
        logger.info("[CONTROLLER][REVIEWS] Iniciando Inclusão de Avaliação.");
        return new ResponseEntity<>(reviewService.save(review), HttpStatus.CREATED);
    }

}