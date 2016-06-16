package com.volomak.movieland.service.impl;

import com.volomak.movieland.dao.MovieDao;
import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.service.CountryService;
import com.volomak.movieland.service.GenreService;
import com.volomak.movieland.service.MovieService;
import com.volomak.movieland.service.ReviewService;
import com.volomak.movieland.service.dto.*;
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
    private MovieDtoConverter movieDtoConverter;

    @Override
    public MovieDetailsDto getById(Long id) {
        log.info("Start query to get movie with movie id {} from DB", id);
        long startTime = System.currentTimeMillis();
        Movie movie = movieDao.getById(id);
        log.info("Finish query to get movie with movie id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return movieDtoConverter.toDetails(movie);
    }

    @Override
    public List<MovieListDto> getMovies() {
        log.info("Start query to get all movies from DB");
        long startTime = System.currentTimeMillis();
        List<MovieListDto> movieListDtos = new ArrayList<>();
        for (Movie movie : movieDao.getMovies()) {
             movieListDtos.add(movieDtoConverter.toList(movie));
        }
        log.info("Finish query to get all movies from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return movieListDtos;
    }
}
