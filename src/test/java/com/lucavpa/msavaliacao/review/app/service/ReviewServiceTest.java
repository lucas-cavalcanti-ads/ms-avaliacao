package com.lucavpa.msavaliacao.review.app.service;

import com.lucavpa.msavaliacao.review.domain.exception.ReviewNotFoundException;
import com.lucavpa.msavaliacao.review.domain.model.Review;
import com.lucavpa.msavaliacao.review.infra.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    // AAA -> Arrange (Prepara), Act (Ação), Assert (Validação)

    @Mock
    ReviewRepository reviewRepository;

    @InjectMocks
    ReviewService reviewService;

    /*
    * Método save
    * */

    @Test
    @DisplayName("Garantir a validação do salvamento de uma avaliação com sucesso")
    void deveSalvarAvaliacaoComSucesso (){
        // Arrange
        LocalDateTime createDate = LocalDateTime.parse("2025-08-12T21:10:57.291192");

        Review review = new Review(1L,2L,3L,4, "Muito bom!", createDate);

        when(reviewRepository.save(review)).thenReturn(review);

        // Act
        Review reviewSaved = reviewService.save(review);

        // Assert
        assertEquals(review, reviewSaved);
        verifyNoMoreInteractions(reviewRepository);

    }

    @Test
    @DisplayName("Garantir que a Service não tenha a responsabilidade de validar entrada dos dados de Entity ")
    void garantirQueNaoHajaValidacoesDeCamposFaltantesNaService () {

        // Arrange
        LocalDateTime createDate = LocalDateTime.parse("2025-08-12T21:10:57.291192");

        Review review = new Review(1L,2L,3L,4, "Muito bom!", createDate);
        review.setPersonId(null);
        review.setRestaurantId(null);
        review.setOrderNumber(10);

        when(reviewRepository.save(review)).thenReturn(review);

        // Act
        Review reviewCreated = reviewService.save(review);

        // Assert
        assertEquals(reviewCreated, review);

    }

    @Test
    @DisplayName("Garantir que o método save realize somente uma interação com o repository")
    void garantirQueMetodoSaveInterajaSomenteUmaVezComRepository () {
        // Arrange
        LocalDateTime createDate = LocalDateTime.parse("2025-08-12T21:10:57.291192");

        Review review = new Review(1L,2L,3L,4, "Muito bom!", createDate);

        when(reviewRepository.save(review)).thenReturn(review);

        // Act
        Review reviewSaved = reviewService.save(review);

        // Assert
        verify(reviewRepository, times(1)).save(review);
        verifyNoMoreInteractions(reviewRepository);
    }

    /*
     * Método getAll
     * */

    @Test
    @DisplayName("Garantir a Consulta de todas as avaliações cadastradas")
    void garantirConsultaTodasAvaliacoesCadastradas () {
        // Arrange
        Review review1 = new Review(1L,2L,3L,1, "Péssimo!", LocalDateTime.parse("2025-08-12T18:10:57.291191"));
        Review review2 = new Review(2L,3L,4L,2, "Não gostei", LocalDateTime.parse("2025-09-12T17:10:57.291190"));
        Review review3 = new Review(3L,4L,5L,3, "Até que bom!", LocalDateTime.parse("2025-10-12T22:10:57.291197"));

        List<Review> reviews = new ArrayList<>();

        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);

        when(reviewRepository.findAll()).thenReturn(reviews);

        // Act
        List<Review> listConsultedReviews = reviewService.getAll();

        // Assert
        assertEquals(reviews, listConsultedReviews);

    }

    @Test
    @DisplayName("Garantir o retorno a consulta de todas as avaliações caso não tenha nenhuma avaliação cadastrada")
    void garantirRetornoConsultaTodasAvaliacoesSemNenhumaAvaliacaoCadastrada () {

        // Arrange
        List<Review> listVoidReview = new ArrayList<>();

        when(reviewRepository.findAll()).thenReturn(listVoidReview);

        // Act
        List<Review> listReviewConsulted = reviewService.getAll();

        // Assert
        assertEquals(listVoidReview, listReviewConsulted);
    }

    @Test
    @DisplayName("Garantir que o método getAll realize somente uma interação com o repository")
    void garantirQueMetodoGetAllInterajaSomenteUmaVezComRepository () {
        // Arrange
        Review review1 = new Review(1L,2L,3L,1, "Péssimo!", LocalDateTime.parse("2025-08-12T18:10:57.291191"));
        Review review2 = new Review(2L,3L,4L,2, "Não gostei", LocalDateTime.parse("2025-09-12T17:10:57.291190"));
        Review review3 = new Review(3L,4L,5L,3, "Até que bom!", LocalDateTime.parse("2025-10-12T22:10:57.291197"));

        List<Review> reviews = new ArrayList<>();

        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);

        when(reviewRepository.findAll()).thenReturn(reviews);

        // Act
        List<Review> listConsultedReviews = reviewService.getAll();

        // Assert
        verify(reviewRepository, times(1)).findAll();
        verifyNoMoreInteractions(reviewRepository);

    }


    /*
     * Método getById
     * */

    @Test
    @DisplayName("Garantir a consulta por ID de uma avaliação com sucesso")
    void garantirConsultaCorretaConsultaAvaliacaoPorId () {

        // Arrange
        Review review = new Review(1L,2L,3L,1, "Péssimo!", LocalDateTime.parse("2025-08-12T18:10:57.291191"));

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        // Act
        Review reviewConsulted = reviewService.getById(1L);

        // Assert
        assertEquals(review, reviewConsulted);

    }

    @Test
    @DisplayName("Garantir o retorno/tratamento correto da consulta de avaliação por ID, caso não encontre uma avaliação")
    void garantirRetornoCorretoConsultaAvaliacaoPorIdCasoNaoEncontre () {

        // Act
        Exception exception = assertThrows(ReviewNotFoundException.class, () -> {
           Review reviewConsulted = reviewService.getById(2L);
        });

        // Assert
        assertEquals("Avaliação de ID 2, não encontrada.", exception.getMessage());
    }

    @Test
    @DisplayName("Garantir que o método getById realize somente uma interação com o repository")
    void garantirQueMetodoGetByIdInterajaSomenteUmaVezComRepository () {

        // Arrange
        Review review = new Review(1L,2L,3L,1, "Péssimo!", LocalDateTime.parse("2025-08-12T18:10:57.291191"));

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        // Act
        Review reviewConsulted = reviewService.getById(1L);

        // Assert
        verify(reviewRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(reviewRepository);

    }

}