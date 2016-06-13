package com.volomak.movieland.service;

import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.service.dto.MovieDto;

import java.util.ArrayList;
import java.util.List;

public interface MovieService {
    Movie getById(int id);

    List<MovieDto> getMovies();

    void add(Movie movie);
}
