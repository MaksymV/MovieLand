package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Movie;

public interface MovieDao {
    Movie getById(int id);
//1111111111111111111111111111
    void add(Movie movie);

}
