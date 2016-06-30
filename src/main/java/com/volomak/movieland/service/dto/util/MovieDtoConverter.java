package com.volomak.movieland.service.dto.util;

import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.service.dto.MovieDetailsDto;
import com.volomak.movieland.service.dto.MovieListDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Genre> genres = movie.getGenres();
        List<Genre> genres2 = new ArrayList<>();
        for(Genre genre : genres){
            genre.setId(null);
            genres2.add(genre);
        }
        movieListDto.setGenres(genres2);
        return movieListDto;
    }
}