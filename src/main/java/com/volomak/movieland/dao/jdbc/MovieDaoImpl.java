package com.volomak.movieland.dao.jdbc;

import com.volomak.movieland.dao.MovieDao;
import com.volomak.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class MovieDaoImpl implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getMovieByIdSQL;

    @Autowired
    private String getMoviesSQL;

    @Override
    public Movie getById(int id) {
        log.info("Start query to get movie with id {} from DB", id);
        long startTime = System.currentTimeMillis();
        Movie movie = jdbcTemplate.queryForObject(getMovieByIdSQL, new Object[] {id},
                new RowMapper<Movie>(){

                    @Override
                    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
                        Movie movie = new Movie();
                        movie.setId(resultSet.getInt("id"));
                        movie.setName(resultSet.getString("name"));
                        movie.setOriginalName(resultSet.getString("original_name"));
                        movie.setYear(resultSet.getLong("year_i"));
                        movie.setDescription(resultSet.getString("description_c"));
                        movie.setRate(resultSet.getDouble("rate_r"));
                        movie.setPrice(resultSet.getDouble("price_r"));
                        return movie;
                    }
                });
        log.info("Finish query to get movie with id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return movie;
    }

    @Override
    public List<Movie> getMovies() {
        log.info("Start query to get movies from DB");
        long startTime = System.currentTimeMillis();
        List<Movie> movies = jdbcTemplate.query(getMoviesSQL,
                new RowMapper<Movie>() {
                    @Override
                    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
                        Movie movie = new Movie();
                        movie.setId(resultSet.getInt("id"));
                        movie.setName(resultSet.getString("name"));
                        movie.setOriginalName(resultSet.getString("original_name"));
                        movie.setYear(resultSet.getLong("year_i"));
                        movie.setDescription(resultSet.getString("description_c"));
                        movie.setRate(resultSet.getDouble("rate_r"));
                        movie.setRate(resultSet.getDouble("rate_r"));
                        return movie;

                    }
                });
        log.info("Finish query to get movies from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return movies;
    }

    @Override
    public void add(Movie movie) {

    }

}
