package com.volomak.movieland.service;

import com.volomak.movieland.entity.Review;
import com.volomak.movieland.service.dto.ReviewRequestDto;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    List<Review> getByMovieId(Long id);

    Review addReview(ReviewRequestDto reviewRequestDto);

    int delReview(Long reviewId, String token);
}
