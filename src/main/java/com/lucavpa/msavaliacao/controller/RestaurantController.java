package com.lucavpa.msavaliacao.controller;

import com.lucavpa.msavaliacao.model.Review;
import com.lucavpa.msavaliacao.model.ReviewAverageDto;
import com.lucavpa.msavaliacao.service.RestaurantService;

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

    @Autowired
    RestaurantService restaurantService;

    private static final Logger logger = LogManager.getLogger(RestaurantController.class);

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Review>> getById (@PathVariable Long id) {
        logger.info("[CONTROLLER][REVIEWS] Iniciando Consulta de todas as Avaliações de um Restaurante.");
        return new ResponseEntity<>(restaurantService.getAllByRestaurantId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/average")
    public ResponseEntity<ReviewAverageDto> getAverageByRestaurantId (@PathVariable Long id) {
        logger.info("[CONTROLLER][REVIEWS] Iniciando Consulta da média da nota de todas as Avaliações de um Restaurante.");
        return new ResponseEntity<>(restaurantService.getOrderAverageReviewByRestaurantId(id), HttpStatus.OK);
    }

}