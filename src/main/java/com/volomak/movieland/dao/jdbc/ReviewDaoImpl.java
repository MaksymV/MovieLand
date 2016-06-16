package com.volomak.movieland.dao.jdbc;

import com.volomak.movieland.dao.ReviewDao;
import com.volomak.movieland.dao.jdbc.mapper.ReviewRowMapper;
import com.volomak.movieland.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ReviewDaoImpl implements ReviewDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private String getReviewsByMovieIdSQL;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Review> getByMovieId(Long id) {
        log.info("Start query to get reviews with movie id {} from DB", id);
        long startTime = System.currentTimeMillis();
        List<Review> reviews = jdbcTemplate.query(getReviewsByMovieIdSQL, new Object[]{id}, new ReviewRowMapper());
        log.info("Finish query to get reviews with movie id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return reviews;
    }
}
