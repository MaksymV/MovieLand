package com.volomak.movieland.service;

import com.volomak.movieland.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getByMovieId(Long id);
}
