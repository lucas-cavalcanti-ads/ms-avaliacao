package com.lucavpa.msavaliacao.restaurant.app.service;

import com.lucavpa.msavaliacao.shared.web.handler.ApiExceptionHandler;
import com.lucavpa.msavaliacao.review.domain.model.Review;
import com.lucavpa.msavaliacao.review.domain.model.ReviewAverageDto;
import com.lucavpa.msavaliacao.review.infra.repository.ReviewRepository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private final ReviewRepository repository;

    private static final Logger logger = LogManager.getLogger(ApiExceptionHandler.class);

    public RestaurantService (ReviewRepository reviewRepository) {
        this.repository = reviewRepository;
    }

    public List<Review> getAllByRestaurantId (Long restaurantId)  {
        List<Review> reviewListConsultedByRestaurantId = repository.findByRestaurantId(restaurantId);

        logger.info("[SERVICE][RESTAURANT] Consulta a lista de todas as avaliações por ID restaurante finalizada com sucesso.");

        return reviewListConsultedByRestaurantId;
    }

    public ReviewAverageDto getOrderAverageReviewByRestaurantId (Long restaurantId)  {

        List<Review> allReviewsByRestaurantId = repository.findByRestaurantId(restaurantId);

        int amountReviews = allReviewsByRestaurantId.size();

        double averageOrderReview = allReviewsByRestaurantId.stream()
                .mapToDouble(Review::getOrderNumber)
                .average()
                .orElse(0.0);

        ReviewAverageDto reviewAverageDto = new ReviewAverageDto(restaurantId, amountReviews, averageOrderReview);

        logger.info("[SERVICE][RESTAURANT] Consulta a média da nota de um restaurante por ID restaurante finalizada com sucesso.");

        return reviewAverageDto;
    }

}
