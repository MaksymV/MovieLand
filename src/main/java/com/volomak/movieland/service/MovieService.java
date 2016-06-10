package com.volomak.movieland.service;

import com.volomak.movieland.entity.Movie;

public interface MovieService {
    Movie getById(int id);

    void add(Movie movie);
}
