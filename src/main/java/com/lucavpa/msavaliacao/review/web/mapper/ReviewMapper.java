package com.lucavpa.msavaliacao.review.web.mapper;

import com.lucavpa.msavaliacao.review.domain.model.Review;
import com.lucavpa.msavaliacao.review.web.request.CreateReviewRequest;
import com.lucavpa.msavaliacao.review.web.response.ReviewResponse;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    Review requestToEntity(CreateReviewRequest request);

    ReviewResponse entityToResponse (Review entity);

    List <ReviewResponse> listEntityToListResponse (List<Review> reviewList);

}
