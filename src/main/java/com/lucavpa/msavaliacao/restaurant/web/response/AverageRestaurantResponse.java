package com.lucavpa.msavaliacao.restaurant.web.response;

public record AverageRestaurantResponse(
        Long restaurantId,
        Integer amountReviews,
        double average
) { }