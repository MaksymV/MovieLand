package com.volomak.movieland.service.dto;

import com.volomak.movieland.entity.Review;

public class ReviewListDtoConverter {
    public ReviewListDto converter(Review review){
        ReviewListDto reviewListDto = new ReviewListDto();
        reviewListDto.setReview(review.getReview());
        return reviewListDto;
    }
}
