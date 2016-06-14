package com.volomak.movieland.service.dto;

import com.volomak.movieland.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieListDtoConverter {
    public MovieListDto convert(Movie movie, List<GenreListDto> genres){
        MovieListDto movieListDto = new MovieListDto();
        movieListDto.setName(movie.getName());
        movieListDto.setOriginalName(movie.getOriginalName());
        movieListDto.setYear(movie.getYear());
        movieListDto.setRate(movie.getRate());
        movieListDto.setGenres(genres);
        return movieListDto;
    }
}
