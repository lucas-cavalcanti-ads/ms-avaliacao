package com.lucavpa.msavaliacao.review.web.controller;

import com.lucavpa.msavaliacao.review.domain.model.Review;
import com.lucavpa.msavaliacao.review.app.service.ReviewService;
import com.lucavpa.msavaliacao.review.web.mapper.ReviewMapper;
import com.lucavpa.msavaliacao.review.web.request.CreateReviewRequest;
import com.lucavpa.msavaliacao.review.web.response.ReviewResponse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    private static final Logger logger = LogManager.getLogger(ReviewsController.class);

    public ReviewsController (ReviewService service, ReviewMapper mapper) {
        this.reviewService = service;
        this.reviewMapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAll () throws Exception{

        logger.info("[CONTROLLER][REVIEWS] Iniciando Consulta de todas as Avaliações.");

        List<Review> reviewList = reviewService.getAll();

        return new ResponseEntity<>(reviewMapper.listEntityToListResponse(reviewList), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReviewResponse> getById (@PathVariable Long id) {

        logger.info("[CONTROLLER][REVIEWS] Iniciando Consulta de uma Avaliação specífica.");

        Review reviewConsulted = reviewService.getById(id);

        return new ResponseEntity<>(reviewMapper.entityToResponse(reviewConsulted), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <ReviewResponse> save (@Valid @RequestBody CreateReviewRequest request) {

        logger.info("[CONTROLLER][REVIEWS] Iniciando Inclusão de Avaliação.");

        Review reviewCreated = reviewService.save(reviewMapper.requestToEntity(request));

        return new ResponseEntity<>(reviewMapper.entityToResponse(reviewCreated), HttpStatus.CREATED);
    }

}