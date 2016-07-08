package com.volomak.movieland.service.dto.util;

import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.entity.User;
import com.volomak.movieland.service.dto.MovieDetailsDto;
import com.volomak.movieland.service.dto.MovieListDto;
import com.volomak.movieland.service.dto.UserRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtoConverter {
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
        List<Genre> genresDto = genres.stream().map(genre -> new Genre(null, genre.getName())).collect(Collectors.toList());
        movieListDto.setGenres(genresDto);
        return movieListDto;
    }

    public UserRequestDto toRequest(User user){
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setId(user.getId());
        userRequestDto.setName(user.getName());
        userRequestDto.setEmail(user.getEmail());
        userRequestDto.setPassword(user.getPassword());
        userRequestDto.setRole(user.getRole());
        return userRequestDto;
    }

}