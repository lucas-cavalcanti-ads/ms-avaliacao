package com.lucavpa.msavaliacao.review.domain.exception;

public class ReviewNotFoundByIdException extends RuntimeException{

    public ReviewNotFoundByIdException(final Long idReview) {
        super("Avaliação de ID " + idReview + ", não encontrada.");
    }
}