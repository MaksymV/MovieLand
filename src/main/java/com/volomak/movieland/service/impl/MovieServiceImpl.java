package com.volomak.movieland.service.impl;

import com.volomak.movieland.dao.MovieDao;
import com.volomak.movieland.entity.Country;
import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.entity.Review;
import com.volomak.movieland.service.CountryService;
import com.volomak.movieland.service.GenreService;
import com.volomak.movieland.service.MovieService;
import com.volomak.movieland.service.ReviewService;
import com.volomak.movieland.service.cache.GenreCache;
import com.volomak.movieland.service.dto.MovieDetailsDto;
import com.volomak.movieland.service.dto.MovieListDto;
import com.volomak.movieland.service.dto.MovieSearchRequestDto;
import com.volomak.movieland.service.dto.util.DtoConverter;
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
    private CountryService countryService;

    @Autowired
    private DtoConverter dtoConverter;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private GenreCache genreCache;

    @Override
    public MovieDetailsDto getById(Long id) {
        log.info("Start query to get movie with movie id {} from DB", id);
        long startTime = System.currentTimeMillis();
        Movie movie = movieDao.getById(id);
        enrichMovie(movie);
        log.info("Finish query to get movie with movie id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return dtoConverter.toDetails(movie);
    }

    @Override
    public List<MovieListDto> getMovies(String ratingOrder, String priceOrder, int page) {
        log.info("Start query to get all movies from DB");
        long startTime = System.currentTimeMillis();
        List<MovieListDto> movieListDtos = new ArrayList<>();
        List<Movie> movies = movieDao.getMovies(ratingOrder, priceOrder, page);
        enrichMovie(movies);
        for (Movie movie : movies) {
             movieListDtos.add(dtoConverter.toList(movie));
        }
        log.info("Finish query to get all movies from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return movieListDtos;
    }

    @Override
    public List<MovieListDto> search(MovieSearchRequestDto movieSearchRequestDto) {
        log.info("Start query to find movies from DB");
        long startTime = System.currentTimeMillis();
        List<MovieListDto> movieListDtos = new ArrayList<>();
        List<Movie> movies = movieDao.search(movieSearchRequestDto);
        enrichMovie(movies);
        for (Movie movie : movies) {
            movieListDtos.add(dtoConverter.toList(movie));
        }
        log.info("Finish query to find movies from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return movieListDtos;
    }

    private List<Movie> enrichMovie(List<Movie> movies){
        for (Movie movie : movies) {
            enrichMovie(movie);
        }
        return movies;
    }

    private Movie enrichMovie(Movie movie){
        List<Long> genreIds = genreService.getIdsByMovieId(movie.getId());
        movie.setGenres(genreCache.getGenres(genreIds));

        List<Country> countries = countryService.getByMovieId(movie.getId());
        movie.setCountries(countries);

        List<Review> reviews = reviewService.getByMovieId(movie.getId());
        movie.setReviews(reviews);
        return movie;
    }
}
