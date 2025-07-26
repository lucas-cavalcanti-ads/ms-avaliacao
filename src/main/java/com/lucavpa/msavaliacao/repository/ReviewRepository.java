package com.lucavpa.msavaliacao.repository;

import com.lucavpa.msavaliacao.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> { }