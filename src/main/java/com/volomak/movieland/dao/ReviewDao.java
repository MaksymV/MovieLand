package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Review;

public interface ReviewDao {
    Review getById(int id);

    void add(Review review);
}
