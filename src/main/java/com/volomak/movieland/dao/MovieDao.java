package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.service.dto.MovieSearchRequestDto;

import java.util.List;

public interface MovieDao {
    Movie getById(Long id);

    List<Movie> getMovies(String ratingOrder, String priceOrder, int fromIndex, int toIndex);

    List<Movie> search(MovieSearchRequestDto movieSearchRequestDto);

    List<Movie> searchDefault();
}
