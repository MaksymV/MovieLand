package com.volomak.movieland.dao.postgres;

import com.volomak.movieland.dao.ReviewDao;
import com.volomak.movieland.dao.postgres.mapper.ReviewRowMapper;
import com.volomak.movieland.entity.Review;
import com.volomak.movieland.service.dto.ReviewRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class ReviewDaoImpl implements ReviewDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final ReviewRowMapper reviewRowMapper = new ReviewRowMapper();

    @Autowired
    private String getReviewsByMovieIdSQL;

    @Autowired
    private String addReviewToMovieSQL;

    @Autowired
    private String delReviewToMovieSQL;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Review> getByMovieId(Long id) {
        log.info("Start query to get reviews with movie id {} from DB", id);
        long startTime = System.currentTimeMillis();
        List<Review> reviews = jdbcTemplate.query(getReviewsByMovieIdSQL, new Object[]{id}, reviewRowMapper);
        log.info("Finish query to get reviews with movie id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return reviews;
    }

    @Override
    public Review addReview(ReviewRequestDto reviewRequestDto) {
        log.info("Start add reviews to movie with id {}", reviewRequestDto.getMoviewId());
        long startTime = System.currentTimeMillis();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(addReviewToMovieSQL);
                preparedStatement.setLong(1, reviewRequestDto.getUserId());
                preparedStatement.setLong(2, reviewRequestDto.getMoviewId());
                preparedStatement.setString(3, reviewRequestDto.getReview());
                return preparedStatement;
            }
        }, keyHolder);
        Review review = new Review();
        //review.setId(keyHolder.getKey().longValue());
        review.setUserId(reviewRequestDto.getUserId());
        review.setMovieId(reviewRequestDto.getMoviewId());
        review.setReview(reviewRequestDto.getReview());
        log.info("Finish add reviews to movie with id {}. It took {} ms", reviewRequestDto.getMoviewId(), System.currentTimeMillis() - startTime);
        return review;
    }

    @Override
    public int delReview(Long reviewId, Long userId) {
        log.info("Start delete reviews with id {}", reviewId);
        long startTime = System.currentTimeMillis();
        int affectedRows = jdbcTemplate.update(delReviewToMovieSQL, new Object[] {reviewId, userId});
        log.info("Finish delete reviews with id {}. It took {} ms", reviewId, System.currentTimeMillis() - startTime);
        return affectedRows;
    }

    @Override
    public int delReview(Long reviewId) {
        log.info("Start delete reviews with id {}", reviewId);
        long startTime = System.currentTimeMillis();
        int affectedRows = jdbcTemplate.update(delReviewToMovieSQL, new Object[] {reviewId});
        log.info("Finish delete reviews with id {}. It took {} ms", reviewId, System.currentTimeMillis() - startTime);
        return affectedRows;
    }
}
