package com.volomak.movieland.service;

import com.volomak.movieland.service.dto.MovieDetailsDto;
import com.volomak.movieland.service.dto.MovieListDto;

import java.util.List;

public interface MovieService {
    MovieDetailsDto getById(Long id);

    List<MovieListDto> getMovies();
}
