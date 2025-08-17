package com.lucavpa.msavaliacao.restaurant.web.controller;

import com.lucavpa.msavaliacao.restaurant.web.response.AverageRestaurantResponse;
import com.lucavpa.msavaliacao.review.domain.model.Review;
import com.lucavpa.msavaliacao.restaurant.app.service.RestaurantService;
import com.lucavpa.msavaliacao.review.web.mapper.ReviewMapper;
import com.lucavpa.msavaliacao.review.web.response.ReviewResponse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final ReviewMapper reviewMapper;

    public RestaurantController(RestaurantService service, ReviewMapper mapper) {
        this.restaurantService = service;
        this.reviewMapper = mapper;
    }

    private static final Logger logger = LogManager.getLogger(RestaurantController.class);

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<ReviewResponse>> getById (@PathVariable Long id) throws Exception {

        logger.info("[CONTROLLER][RESTAURANT] Iniciando Consulta de todas as Avaliações de um Restaurante.");

        List<Review> reviewList = restaurantService.getAllByRestaurantId(id);

        return new ResponseEntity<>(reviewMapper.listEntityToListResponse(reviewList), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/average")
    public ResponseEntity<AverageRestaurantResponse> getAverageByRestaurantId (@PathVariable Long id) throws Exception{

        logger.info("[CONTROLLER][RESTAURANT] Iniciando Consulta da média da nota de todas as Avaliações de um Restaurante.");

        AverageRestaurantResponse averageRestaurant = restaurantService.getOrderAverageReviewByRestaurantId(id);

        return new ResponseEntity<>(averageRestaurant, HttpStatus.OK);
    }

}