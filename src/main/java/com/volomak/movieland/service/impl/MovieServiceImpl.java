package com.volomak.movieland.service.impl;

import com.volomak.movieland.dao.MovieDao;
import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.service.GenreService;
import com.volomak.movieland.service.MovieService;
import com.volomak.movieland.service.dto.GenreListDto;
import com.volomak.movieland.service.dto.MovieListDto;
import com.volomak.movieland.service.dto.MovieListDtoConverter;
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
    private GenreService genreService;

    @Autowired
    private MovieListDtoConverter movieListDtoConverter;

    @Override
    public Movie getById(int id) {
        return movieDao.getById(id);
    }

    @Override
    public List<MovieListDto> getMovies() {
        List<MovieListDto> movieListDtos = new ArrayList<>();
        for (Movie movie : movieDao.getMovies()) {
             log.info("Start query to get genres with movie id {} from DB", movie.getId());
             long startTime = System.currentTimeMillis();
             List<GenreListDto> genres = genreService.getByMovieId(movie.getId());
             log.info("Finish query to get genres with movie id {} from DB. It took {} ms", movie.getId(), System.currentTimeMillis() - startTime);
             movieListDtos.add(movieListDtoConverter.convert(movie, genres));
        }
        return movieListDtos;
    }

    @Override
    public void add(Movie movie) {
        movieDao.add(movie);
    }
}
