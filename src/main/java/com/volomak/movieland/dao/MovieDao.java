package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Movie;

public interface MovieDao {
    Movie getById(int id);
//2222222222222222222222
    void add(Movie movie);

}
