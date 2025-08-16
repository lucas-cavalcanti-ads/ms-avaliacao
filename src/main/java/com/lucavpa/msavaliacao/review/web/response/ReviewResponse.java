package com.lucavpa.msavaliacao.review.web.response;

import java.time.LocalDateTime;

public record ReviewResponse(
        Integer id,
        Long restaurantId,
        Long personId,
        Integer orderNumber,
        String comment,
        LocalDateTime createDate
) { }