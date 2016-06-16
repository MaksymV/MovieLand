package com.volomak.movieland.service.dto;

import com.volomak.movieland.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDtoConverter {
    public MovieDetailsDto toDetails(Movie movie){
        MovieDetailsDto movieDetailsDto = new MovieDetailsDto();
        movieDetailsDto.setName(movie.getName());
        movieDetailsDto.setOriginalName(movie.getOriginalName());
        movieDetailsDto.setYear(movie.getYear());
        movieDetailsDto.setCountries(movie.getCountries());
        movieDetailsDto.setGenres(movie.getGenres());
        movieDetailsDto.setDescription(movie.getDescription());
        movieDetailsDto.setReviews(movie.getReviews());
        movieDetailsDto.setRate(movie.getRate());
        return movieDetailsDto;
    }

    public MovieListDto toList(Movie movie){
        MovieListDto movieListDto = new MovieListDto();
        movieListDto.setName(movie.getName());
        movieListDto.setOriginalName(movie.getOriginalName());
        movieListDto.setYear(movie.getYear());
        movieListDto.setRate(movie.getRate());
        movieListDto.setGenres(movie.getGenres());
        return movieListDto;
    }
}
