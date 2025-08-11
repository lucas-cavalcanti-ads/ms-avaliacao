package com.lucavpa.msavaliacao.review.domain.exception;

public class ReviewNotFoundByRestaurantIdException extends RuntimeException{

    public ReviewNotFoundByRestaurantIdException(final Long restaurantId) {
        super("Nenhuma avaliação encontrada para o restaurante de ID  " + restaurantId);
    }


}
