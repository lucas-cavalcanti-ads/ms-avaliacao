package com.lucavpa.msavaliacao.review.domain.exception;

public class ReviewNotFoundException extends RuntimeException{

    public ReviewNotFoundException() {

        super("Nenhuma avaliação encontrada.");

    }
}