package com.lucavpa.msavaliacao.restaurant.app.service;

import com.lucavpa.msavaliacao.restaurant.domain.exception.RestaurantNotFoundException;
import com.lucavpa.msavaliacao.restaurant.web.response.AverageRestaurantResponse;
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


@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    /*
     * Utilizar o método AAA -> Arrange (Prepara), Act (Ação), Assert (Validação)
     * */

    @Mock
    ReviewRepository repository;

    @InjectMocks
    RestaurantService restaurantService;

    /*
    * getAllByRestaurantId
    * */

    @Test
    @DisplayName("Garantir a consulta de todas as avaliações por ID do restaurante")
    void garantirConsultaTodasAvaliacoesPorIdRestaurante () throws Exception {

        // Arrange
        Review review1 = new Review(1L,2L,3L,4, "Muito bom!", LocalDateTime.parse("2025-08-12T21:10:57.291192"));
        Review review2 = new Review(2L,2L,4L,3, "Bom!", LocalDateTime.parse("2025-07-12T11:43:57.291198"));

        Long restaurantId = 2L;

        List<Review> reviewList = new ArrayList<>();

        reviewList.add(review1);
        reviewList.add(review2);

        when(repository.findByRestaurantId(2L)).thenReturn(reviewList);

        // Act
        List<Review> reviewListConsulted = restaurantService.getAllByRestaurantId(restaurantId);

        // Assert
        assertEquals(reviewList, reviewListConsulted);

    }

    @Test
    @DisplayName("Garantir o tratamento da consulta sem avaliações por ID do restaurante")
    void garantirTratamentoConsultaSemAvaliacoesPorIdRestaurante () {

        Long restaurantId = 2L;

        // Act + Assert
        Exception exception = assertThrows(RestaurantNotFoundException.class, ()-> {
           restaurantService.getAllByRestaurantId(restaurantId);
        });

        // Assert
        assertEquals("Não foi encontrada nenhuma avaliação para o restaurante de ID 2.", exception.getMessage());
    }

    @Test
    @DisplayName("Garantir que o método getAllByRestaurantId tenha somente uma interação com o repository")
    void garantirQueMetodoGetAllByRestaurantIdInterajaSomenteUmaVezComRepository () throws Exception {

        // Arrange
        Review review1 = new Review(1L,2L,3L,4, "Muito bom!", LocalDateTime.parse("2025-08-12T21:10:57.291192"));
        Review review2 = new Review(2L,2L,4L,3, "Bom!", LocalDateTime.parse("2025-07-12T11:43:57.291198"));

        Long restaurantId = 2L;

        List<Review> reviewList = new ArrayList<>();

        reviewList.add(review1);
        reviewList.add(review2);

        when(repository.findByRestaurantId(restaurantId)).thenReturn(reviewList);

        // Act
        List<Review> reviewListConsulted = restaurantService.getAllByRestaurantId(restaurantId);

        // Assert
        verify(repository, times(1)).findByRestaurantId(restaurantId);
        verifyNoMoreInteractions(repository);
    }

    /*
     * getOrderAverageReviewByRestaurantId
     * */

    @Test
    @DisplayName("Garantir a consulta a média de todas as avaliações por ID do restaurante")
    void garantirCalculoMediaTodasAvaliacoesPorIdRestaurante () throws Exception {

        // Arrange
        Review review1 = new Review(1L,2L,3L,1, "Muito bom!", LocalDateTime.parse("2025-08-12T21:10:57.291192"));
        Review review2 = new Review(2L,2L,4L,3, "Bom!", LocalDateTime.parse("2025-07-12T11:43:57.291198"));

        Long restaurantId = 2L;

        List<Review> reviewList = new ArrayList<>();

        reviewList.add(review1);
        reviewList.add(review2);

        when(repository.findByRestaurantId(restaurantId)).thenReturn(reviewList);

        // Act
        AverageRestaurantResponse averageRestaurantResponse = restaurantService.getOrderAverageReviewByRestaurantId(restaurantId);

        // Assert
        assertEquals(2, averageRestaurantResponse.average());
        assertEquals(2, averageRestaurantResponse.restaurantId());
        assertEquals(2, averageRestaurantResponse.amountReviews());
    }

    @Test
    @DisplayName("Garantir o tratamento do calculo de média sem avaliações por ID do restaurante")
    void garantirTratamentoCalculoMedioSemAvaliacoesPorIdRestaurante () {

        // Arrange
        Long restaurantId = 2L;

        // Act + Assert
        Exception exception = assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantService.getOrderAverageReviewByRestaurantId(restaurantId);
        });

        // Assert
        assertEquals("Não foi encontrada nenhuma avaliação para o restaurante de ID 2.", exception.getMessage());

    }

    @Test
    @DisplayName("Garantir que o método getOrderAverageReviewByRestaurantId tenha somente uma interação com o repository")
    void garantirQueMetodoGetOrderAverageReviewByRestaurantIdInterajaSomenteUmaVezComRepository () throws Exception {

        // Arrange
        Review review1 = new Review(1L,2L,3L,1, "Muito bom!", LocalDateTime.parse("2025-08-12T21:10:57.291192"));
        Review review2 = new Review(2L,2L,4L,3, "Bom!", LocalDateTime.parse("2025-07-12T11:43:57.291198"));

        Long restaurantId = 2L;

        List<Review> reviewList = new ArrayList<>();

        reviewList.add(review1);
        reviewList.add(review2);

        when(repository.findByRestaurantId(restaurantId)).thenReturn(reviewList);

        // Act
        AverageRestaurantResponse averageRestaurantResponse = restaurantService.getOrderAverageReviewByRestaurantId(restaurantId);

        // Assert
        verify(repository, times(1)).findByRestaurantId(restaurantId);
        verifyNoMoreInteractions(repository);
    }

}