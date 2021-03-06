package com.volomak.movieland.dao.postgres;

import com.volomak.movieland.dao.MovieDao;
import com.volomak.movieland.dao.postgres.mapper.MovieRowMapper;
import com.volomak.movieland.dao.postgres.util.QueryBuilder;
import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.service.dto.MovieSearchRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDaoImpl implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    public static final MovieRowMapper movieRowMapper = new MovieRowMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getMovieByIdSQL;

    @Autowired
    private QueryBuilder queryBuilder;

    @Override
    public Movie getById(Long id) {
        log.info("Start query to get movie with id {} from DB", id);
        long startTime = System.currentTimeMillis();
        Movie movie = jdbcTemplate.queryForObject(getMovieByIdSQL, new Object[] {id}, movieRowMapper);
        log.info("Finish query to get movie with id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return movie;
    }

    @Override
    public List<Movie> getMovies(String ratingOrder, String priceOrder, int page) {
        log.info("Start query to get movies from DB");
        long startTime = System.currentTimeMillis();
        List<Movie> movies = jdbcTemplate.query(queryBuilder.getMovies(ratingOrder, priceOrder, page), movieRowMapper);
        log.info("Finish query to get movies from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return movies;
    }

    @Override
    public List<Movie> search(MovieSearchRequestDto movieSearchRequestDto) {
        log.info("Start searching of movies");
        long startTime = System.currentTimeMillis();
        List<Movie> movies = jdbcTemplate.query(queryBuilder.movieSearch(movieSearchRequestDto), movieRowMapper);
        log.info("Finish searching of movies. It took {} ms", System.currentTimeMillis() - startTime);
        return movies;

    }

}
