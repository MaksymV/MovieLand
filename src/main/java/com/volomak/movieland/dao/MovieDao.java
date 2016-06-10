package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Movie;

public interface MovieDao {
    MovieDao getById(int id);

    void add(Movie movie);
}
