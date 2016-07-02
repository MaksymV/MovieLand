package com.volomak.movieland.service;

import com.volomak.movieland.service.dto.MovieDetailsDto;
import com.volomak.movieland.service.dto.MovieListDto;
import com.volomak.movieland.service.dto.MovieSearchRequestDto;

import java.util.List;

public interface MovieService {
    MovieDetailsDto getById(Long id);

    List<MovieListDto> getMovies(String ratingOrder, String priceOrder, int page);

    List<MovieListDto> search(MovieSearchRequestDto movieSearchRequestDto);
}
