package com.volomak.movieland.service;

import com.volomak.movieland.entity.Movie;

import java.util.ArrayList;

public interface MovieService {
    Movie getById(int id);

    ArrayList<Movie> getMovies();

    void add(Movie movie);
}
