package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Review;

import java.util.List;

public interface ReviewDao {
    Review getById(Long id);

    List<Review> getByMovieId(Long id);
}
