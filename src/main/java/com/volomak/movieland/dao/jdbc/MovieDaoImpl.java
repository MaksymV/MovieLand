package com.volomak.movieland.dao.jdbc;

import com.volomak.movieland.dao.MovieDao;
import com.volomak.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.volomak.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MovieDaoImpl implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    private String getMovieByIdSQL;

    @Autowired
    private String addMovieSQL;

    @Override
    public Movie getById(int id) {
        log.info("Start query to get movie with id {} from DB", id);
        long startTime = System.currentTimeMillis();
        Movie movie = jdbcTemplate.queryForObject(getMovieByIdSQL, new Object[] {id}, new MovieRowMapper());
        log.info("Finish query to get movie with id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return movie;
    }

    @Override
    public void add(Movie movie) {
        log.info("Start query to add movie {} to DB", movie);
        long startTime = System.currentTimeMillis();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", movie.getName());
        parameterSource.addValue("original_name", movie.getOriginalName());
        parameterSource.addValue("year_i", movie.getYear());
        parameterSource.addValue("descriptuin_c", movie.getDescription());
        parameterSource.addValue("rate_r", movie.getRate());
        parameterSource.addValue("price_r", movie.getPrice());
        namedJdbcTemplate.update(addMovieSQL, parameterSource);
        log.info("Finish query to add movie {} to DB. It took {} ms", movie, System.currentTimeMillis() - startTime);
    }
}
