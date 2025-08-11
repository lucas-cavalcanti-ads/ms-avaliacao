package com.lucavpa.msavaliacao.review.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReviewAverageDto {

    private Long restaurantId;

    private int amountReviews;

    private double average;


}
