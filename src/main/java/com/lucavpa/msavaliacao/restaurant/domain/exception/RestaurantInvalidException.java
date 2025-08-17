package com.lucavpa.msavaliacao.restaurant.domain.exception;

public class RestaurantInvalidException extends RuntimeException {

    public RestaurantInvalidException(Long restaurantId) {

        super("O ID " + restaurantId + " do restaurante é inválido.");

    }
}
