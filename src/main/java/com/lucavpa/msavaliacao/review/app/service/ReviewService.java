package com.lucavpa.msavaliacao.review.app.service;

import com.lucavpa.msavaliacao.restaurant.domain.exception.RestaurantNotFoundException;
import com.lucavpa.msavaliacao.review.domain.exception.ReviewNotFoundByIdException;
import com.lucavpa.msavaliacao.review.domain.exception.ReviewNotFoundException;
import com.lucavpa.msavaliacao.review.infra.repository.ReviewRepository;
import com.lucavpa.msavaliacao.review.domain.model.Review;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service
public class ReviewService {

    private final ReviewRepository repository;

    private static final Logger logger = LogManager.getLogger(ReviewService.class);

    public ReviewService (ReviewRepository reviewRepository) {
        this.repository = reviewRepository;
    }

    public Review save (Review review){

        Review reviewSaved = repository.save(review);

        logger.info("[SERVICE][REVIEWS] Salvamento da avaliação finalizado com sucesso.");

        return reviewSaved;
    }

    public List<Review> getAll () throws Exception {
        List<Review> reviewListConsulted = repository.findAll();

        if (this.haveReviewList(reviewListConsulted)){
            logger.info("[SERVICE][REVIEWS] Consulta a lista de todas as avaliações finalizada com sucesso.");
            return reviewListConsulted;
        }

        return Collections.emptyList();

    }

    public Review getById (Long id) {

        Review reviewByIdConsulted = repository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundByIdException(id));

        logger.info("[SERVICE][REVIEWS] Consulta a lista de todas as avaliações por ID finalizada com sucesso.");

        return reviewByIdConsulted;
    }

    private boolean haveReviewList (List<Review> reviewList) throws Exception{
        if(reviewList.size() > 0) return true;
        throw new ReviewNotFoundException();
    }

}