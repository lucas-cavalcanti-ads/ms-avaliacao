package com.lucavpa.msavaliacao.restaurant.domain.exception;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(Long restaurantId) {

        super("Não foi encontrada nenhuma avaliação para o restaurante de ID " + restaurantId + ".");

    }
}
