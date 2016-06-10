package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Movie;

public interface MovieDao {
    Movie getById(int id);

    void add(Movie movie);
}
