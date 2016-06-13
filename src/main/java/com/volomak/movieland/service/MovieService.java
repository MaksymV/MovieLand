package com.volomak.movieland.service;

import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.service.dto.MovieListDto;

import java.util.List;

public interface MovieService {
    Movie getById(int id);

    List<MovieListDto> getMovies();

    void add(Movie movie);
}
