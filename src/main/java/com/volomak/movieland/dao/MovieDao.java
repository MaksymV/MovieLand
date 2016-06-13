package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Movie;

import java.util.List;

public interface MovieDao {
    Movie getById(int id);

    List<Movie> getMovies();

    void add(Movie movie);
}
