package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Review;

import java.util.List;

public interface ReviewDao {
    Review getById(int id);

    List<Review> getByMovieId(int id);

    void add(Review review);
}
