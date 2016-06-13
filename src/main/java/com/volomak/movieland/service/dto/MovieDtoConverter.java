package com.volomak.movieland.service.dto;

import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieDtoConverter {

    public MovieDto conver(Movie movie, List<Genre> genres){
        MovieDto movieDto = new MovieDto();
        movieDto.setName(movie.getName());
        movieDto.setOriginalName(movie.getOriginalName());
        movieDto.setYear(movie.getYear());
        movieDto.setRate(movie.getRate());
        movieDto.setGenres(genres);
        return movieDto;

    }
}
