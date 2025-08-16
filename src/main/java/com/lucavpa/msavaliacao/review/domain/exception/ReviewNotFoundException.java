package com.lucavpa.msavaliacao.review.domain.exception;

public class ReviewNotFoundException extends RuntimeException{

    public ReviewNotFoundException (final Long idReview) {
        super("Avaliação de ID " + idReview + ", não encontrada.");
    }
}