package com.lucavpa.msavaliacao.review.web.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateReviewRequest(

        @NotNull (message = "O ID do restaurante é obrigatório.") Long restaurantId,
        @NotNull (message = "O ID do cliente é obrigatório.") Long personId,
        @NotNull (message = "A nota da avaliação varia entre 1 e 5.") @Min(1) @Max(5) Integer orderNumber,
        String comment

) { }
