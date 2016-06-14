package com.volomak.movieland.dao.jdbc;

import com.volomak.movieland.dao.ReviewDao;
import com.volomak.movieland.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ReviewDaoImpl implements ReviewDao {
    @Autowired
    private String getReviewsByMovieIdSQL;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Review getById(int id) {
        return null;
    }

    @Override
    public List<Review> getByMovieId(int id) {
        List<Review> reviews = jdbcTemplate.query(getReviewsByMovieIdSQL,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, id);
                    }
                },
                new RowMapper<Review>() {
                    @Override
                    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
                        Review review = new Review();
                        review.setReview(resultSet.getString("review_c"));
                        return review;
                    }
                }

        );
        return reviews;
    }

    @Override
    public void add(Review review) {

    }
}
