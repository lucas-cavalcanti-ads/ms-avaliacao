package com.lucavpa.msavaliacao.restaurant.app.service;

import com.lucavpa.msavaliacao.restaurant.domain.exception.RestaurantInvalidException;
import com.lucavpa.msavaliacao.restaurant.domain.exception.RestaurantNotFoundException;
import com.lucavpa.msavaliacao.restaurant.web.response.AverageRestaurantResponse;
import com.lucavpa.msavaliacao.review.domain.model.Review;
import com.lucavpa.msavaliacao.review.infra.repository.ReviewRepository;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private final ReviewRepository repository;

    private static final Logger logger = LogManager.getLogger(RestaurantService.class);

    public RestaurantService (ReviewRepository reviewRepository) {
        this.repository = reviewRepository;
    }

    public List<Review> getAllByRestaurantId (Long restaurantId) throws Exception {

        return this.allByRestaurantId(restaurantId);

    }

    public AverageRestaurantResponse getOrderAverageReviewByRestaurantId (Long restaurantId) throws Exception {

        List<Review> allReviewsByRestaurantId = this.allByRestaurantId(restaurantId);

        Integer amountReviews = allReviewsByRestaurantId.size();

        double averageOrderReview = this.calculatedAverage(allReviewsByRestaurantId);

        AverageRestaurantResponse averageRestaurantResponse = new AverageRestaurantResponse (restaurantId, amountReviews, averageOrderReview);

        logger.info("[SERVICE][RESTAURANT] Consulta a média da nota de um restaurante por ID restaurante finalizada com sucesso.");

        return averageRestaurantResponse;
    }

    private double calculatedAverage (List<Review> reviewList) {

        return reviewList.stream()
                .mapToDouble(Review::getOrderNumber)
                .average()
                .orElse(0.0);

    }

    private List<Review> allByRestaurantId (Long restaurantId) throws Exception {

        if (this.restaurantIsValid(restaurantId)) {

            List<Review> reviewListConsultedByRestaurantId = repository.findByRestaurantId(restaurantId);

            if (this.haveReviewList(reviewListConsultedByRestaurantId, restaurantId)) {

                logger.info("[SERVICE][RESTAURANT] Consulta a lista de todas as avaliações por ID restaurante finalizada com sucesso.");

                return reviewListConsultedByRestaurantId;

            }
        }

        return Collections.emptyList();
    }

    private boolean restaurantIsValid (Long id) throws Exception {
        if (id > 0 && this.restaurantIsNotNull(id)) return true;
        throw new RestaurantInvalidException(id);
    }

    private boolean restaurantIsNotNull (Long id) {
        return id != null;
    }

    private boolean haveReviewList (List<Review> reviewList, Long restaurantId) throws Exception{
        if(reviewList.size() > 0) return true;
        throw new RestaurantNotFoundException(restaurantId);
    }

}
