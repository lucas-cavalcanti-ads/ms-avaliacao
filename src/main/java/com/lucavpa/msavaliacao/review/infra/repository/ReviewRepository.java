package com.lucavpa.msavaliacao.review.infra.repository;

import com.lucavpa.msavaliacao.review.domain.model.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findByRestaurantId (Long id);

}