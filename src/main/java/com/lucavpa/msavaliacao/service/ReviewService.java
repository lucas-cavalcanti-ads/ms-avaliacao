package com.lucavpa.msavaliacao.service;

import com.lucavpa.msavaliacao.model.Review;
import com.lucavpa.msavaliacao.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    public Review save (Review review){
        System.out.println("[LOG][SERVICE] Method save");
        return repository.save(review);
    }

}
