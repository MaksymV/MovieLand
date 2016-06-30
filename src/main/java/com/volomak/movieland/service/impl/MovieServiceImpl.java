package com.volomak.movieland.service.impl;

import com.volomak.movieland.dao.MovieDao;
import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.service.MovieService;
import com.volomak.movieland.service.dto.MovieDetailsDto;
import com.volomak.movieland.service.dto.MovieListDto;
import com.volomak.movieland.service.dto.MovieSearchRequestDto;
import com.volomak.movieland.service.dto.util.MovieDtoConverter;
import com.volomak.movieland.util.Constant;
import com.volomak.movieland.util.MovieComparator;
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
    public List<MovieListDto> getMovies(String ratingOrder, String priceOrder, int page) {
        log.info("Start query to get all movies from DB");
        long startTime = System.currentTimeMillis();
        List<MovieListDto> movieListDtos = new ArrayList<>();
        int fromIndex = (page - 1) * Constant.MOVIES_PER_PAGE + 1;
        int toIndex = (page) * Constant.MOVIES_PER_PAGE;
        List<Movie> movies = movieDao.getMovies(ratingOrder, priceOrder, fromIndex, toIndex);
        movies.sort(new MovieComparator(ratingOrder, priceOrder));
        for (Movie movie : movies) {
             movieListDtos.add(movieDtoConverter.toList(movie));
        }
        log.info("Finish query to get all movies from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return movieListDtos;
    }

    @Override
    public List<MovieListDto> search(MovieSearchRequestDto movieSearchRequestDto) {
        log.info("Start query to find movies from DB");
        long startTime = System.currentTimeMillis();
        List<MovieListDto> movieListDtos = new ArrayList<>();
        for (Movie movie : movieDao.search(movieSearchRequestDto)) {
            movieListDtos.add(movieDtoConverter.toList(movie));
        }
        log.info("Finish query to find movies from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return movieListDtos;
    }

    @Override
    public List<MovieListDto> searchDefault() {
        log.info("Start query to find DEFAULT movie from DB");
        long startTime = System.currentTimeMillis();
        List<MovieListDto> movieListDtos = new ArrayList<>();
        for (Movie movie : movieDao.searchDefault()) {
            movieListDtos.add(movieDtoConverter.toList(movie));
        }
        log.info("Finish query to find DEFAULT movie from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return movieListDtos;
    }
}
