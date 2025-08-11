package com.lucavpa.msavaliacao.exception;

public class ReviewNotFoundException extends RuntimeException{

    public ReviewNotFoundException (final Long idReview) {
        super("Avaliação de ID  " + idReview + ", não encontrada.");
    }
}