package com.lucavpa.msavaliacao.restaurant.web.controller;

import com.lucavpa.msavaliacao.review.domain.model.Review;
import com.lucavpa.msavaliacao.review.domain.model.ReviewAverageDto;
import com.lucavpa.msavaliacao.restaurant.app.service.RestaurantService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService service) { this.restaurantService = service; }

    private static final Logger logger = LogManager.getLogger(RestaurantController.class);

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Review>> getById (@PathVariable Long id) {
        logger.info("[CONTROLLER][RESTAURANT] Iniciando Consulta de todas as Avaliações de um Restaurante.");
        return new ResponseEntity<>(restaurantService.getAllByRestaurantId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/average")
    public ResponseEntity<ReviewAverageDto> getAverageByRestaurantId (@PathVariable Long id) {
        logger.info("[CONTROLLER][RESTAURANT] Iniciando Consulta da média da nota de todas as Avaliações de um Restaurante.");
        return new ResponseEntity<>(restaurantService.getOrderAverageReviewByRestaurantId(id), HttpStatus.OK);
    }

}