package com.volomak.movieland.service.dto;

import com.volomak.movieland.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDetailsDtoConverter {
    public MovieDetailsDto convert(Movie movie, List<GenreListDto> genres, List<CountryListDto> countries, List<ReviewListDto> reviews){
        MovieDetailsDto movieDetailsDto = new MovieDetailsDto();
        movieDetailsDto.setName(movie.getName());
        movieDetailsDto.setOriginalName(movie.getOriginalName());
        movieDetailsDto.setYear(movie.getYear());
        movieDetailsDto.setCountries(countries);
        movieDetailsDto.setGenres(genres);
        movieDetailsDto.setDescription(movie.getDescription());
        movieDetailsDto.setReviews(reviews);
        movieDetailsDto.setRate(movie.getRate());
        return movieDetailsDto;
    }
}
