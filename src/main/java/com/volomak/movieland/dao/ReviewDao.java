package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Review;
import com.volomak.movieland.service.dto.ReviewRequestDto;

import java.util.List;

public interface ReviewDao {
    List<Review> getByMovieId(Long id);

    Review addReview(ReviewRequestDto reviewRequestDto);

    int delReview(Long reviewId, Long userId);
}
