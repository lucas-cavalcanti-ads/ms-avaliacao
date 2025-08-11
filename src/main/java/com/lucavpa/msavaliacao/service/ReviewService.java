package com.lucavpa.msavaliacao.service;

import com.lucavpa.msavaliacao.exception.ReviewNotFoundException;
import com.lucavpa.msavaliacao.handler.ApiExceptionHandler;
import com.lucavpa.msavaliacao.repository.ReviewRepository;
import com.lucavpa.msavaliacao.model.Review;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    private static final Logger logger = LogManager.getLogger(ApiExceptionHandler.class);

    public ReviewService (ReviewRepository reviewRepository) {
        this.repository = reviewRepository;
    }

    public Review save (Review review){

        Review reviewSaved = repository.save(review);

        logger.info("[SERVICE][REVIEWS] Salvamento da avaliação finalizado com sucesso.");

        return reviewSaved;
    }

    public List<Review> getAll () {
        List<Review> reviewListConsulted = repository.findAll();

        logger.info("[SERVICE][REVIEWS] Consulta a lista de todas as avaliações finalizada com sucesso.");

        return reviewListConsulted;
    }

    public Review getById (Long id) {

        Review reviewByIdConsulted = repository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));

        logger.info("[SERVICE][REVIEWS] Consulta a lista de todas as avaliações por ID finalizada com sucesso.");

        return reviewByIdConsulted;
    }

}
