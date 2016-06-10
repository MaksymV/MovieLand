package com.volomak.movieland.dao.jdbc.mapper;

import com.volomak.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setId((long) resultSet.getInt("id"));
        movie.setName(resultSet.getString("name"));
        movie.setOriginalName(resultSet.getString("original_name"));
        movie.setYear(resultSet.getLong("year_i"));
        movie.setDescription(resultSet.getString("description_c"));
        movie.setRate(resultSet.getDouble("rate_r"));
        movie.setPrice(resultSet.getDouble("price_r"));
        return movie;
    }
}
