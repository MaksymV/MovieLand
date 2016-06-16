package com.volomak.movieland.dao.jdbc.mapper;

import com.volomak.movieland.entity.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by grey4 on 16.06.2016.
 */
public class ReviewRowMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getLong("id"));
        review.setMovieId(resultSet.getLong("movie_id"));
        review.setUserId(resultSet.getLong("user_id"));
        review.setReview(resultSet.getString("review_c"));
        return review;
    }
}
