package com.volomak.movieland.service.impl;

import com.volomak.movieland.dao.GenreDao;
import com.volomak.movieland.dao.MovieDao;
import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.service.MovieService;
import com.volomak.movieland.service.dto.MovieDto;
import com.volomak.movieland.service.dto.MovieDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private MovieDtoConverter movieDtoConverter;

    @Override
    public Movie getById(int id) {
        return movieDao.getById(id);
    }

    @Override
    public List<MovieDto> getMovies() {
        List<MovieDto> movieDtos = new ArrayList<>();
        for (Movie movie : movieDao.getMovies()) {
             List<Genre> genres = genreDao.getByMovieId(Math.toIntExact(movie.getId()));
             movieDtos.add(movieDtoConverter.conver(movie, genres));
        }
        return movieDtos;
    }

    @Override
    public void add(Movie movie) {
        movieDao.add(movie);
    }
}
