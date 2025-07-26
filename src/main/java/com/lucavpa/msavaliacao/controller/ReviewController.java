package com.lucavpa.msavaliacao.controller;

import com.lucavpa.msavaliacao.model.Review;
import com.lucavpa.msavaliacao.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping
    public ResponseEntity <Review> save (@RequestBody Review review){
        return new ResponseEntity<>(reviewService.save(review), HttpStatus.CREATED);
    }
}