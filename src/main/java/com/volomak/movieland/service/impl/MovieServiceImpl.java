package com.volomak.movieland.service.impl;

import com.volomak.movieland.dao.GenreDao;
import com.volomak.movieland.dao.MovieDao;
import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.service.MovieService;
import com.volomak.movieland.service.dto.MovieDto;
import com.volomak.movieland.service.dto.MovieDtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final Logger log = LoggerFactory.getLogger(getClass());

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
             log.info("Start query to get genres with movie id {} from DB", movie.getId());
             long startTime = System.currentTimeMillis();
             List<Genre> genres = genreDao.getByMovieId(movie.getId());
             log.info("Finish query to get genres with movie id {} from DB. It took {} ms", movie.getId(), System.currentTimeMillis() - startTime);
             movieDtos.add(movieDtoConverter.conver(movie, genres));
        }
        return movieDtos;
    }

    @Override
    public void add(Movie movie) {
        movieDao.add(movie);
    }
}
