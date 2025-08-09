package com.lucavpa.msavaliacao.service;

import com.lucavpa.msavaliacao.model.Review;
import com.lucavpa.msavaliacao.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    public Review save (Review review){
        System.out.println("[LOG][SERVICE] Method save");
        return repository.save(review);
    }

    public List<Review> getAll () {
        System.out.println("[LOG][SERVICE] getAll");
        return repository.findAll();
    }

    public Review getById (Long id) {
        System.out.println("[LOG][SERVICE] getAll");
        return repository.findById(id).get();
    }

}
