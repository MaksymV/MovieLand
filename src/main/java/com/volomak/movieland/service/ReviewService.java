package com.volomak.movieland.service;

import com.volomak.movieland.entity.Review;
import com.volomak.movieland.service.dto.ReviewRequestDto;

import java.util.List;

public interface ReviewService {
    List<Review> getByMovieId(Long id);

    Review addReview(ReviewRequestDto reviewRequestDto);

    int delReview(ReviewRequestDto eviewRequestDto);
}
