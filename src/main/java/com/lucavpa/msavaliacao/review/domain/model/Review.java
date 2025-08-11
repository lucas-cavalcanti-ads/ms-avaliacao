package com.lucavpa.msavaliacao.review.domain.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_review")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O ID do restaurante é obrigatório.")
    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @NotNull(message = "O ID do cliente é obrigatório.")
    @Column(name = "person_id", nullable = false)
    private Long personId;

    @NotNull(message = "A nota da avaliação é obrigatório.")
    @Column(name = "order_number", nullable = false)
    private int orderNumber;

    @Column(name = "comment", length = 100, nullable = false)
    private String comment;

    @CreatedDate
    @Column(name="create_date", updatable = false, nullable = false)
    private LocalDateTime createDate;

}