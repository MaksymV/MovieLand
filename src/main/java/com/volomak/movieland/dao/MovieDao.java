package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Movie;

import java.util.List;

public interface MovieDao {
    Movie getById(Long id);

    List<Movie> getMovies();

    List<Movie> search();
}
