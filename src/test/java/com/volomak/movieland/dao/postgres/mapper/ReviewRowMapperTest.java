package com.volomak.movieland.dao.postgres.mapper;

import com.volomak.movieland.entity.Review;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapperTest {
    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        initLocalMocks();
    }

    @Test
    public void mapRowTest() throws SQLException {
        ReviewRowMapper reviewRowMapper = new ReviewRowMapper();

        Review review = reviewRowMapper.mapRow(resultSet, 0);
        Assert.assertEquals(review.getId().longValue(), 1L);
        Assert.assertEquals(review.getMovieId().longValue(), 1L);
        Assert.assertEquals(review.getUserId().longValue(), 1L);
        Assert.assertEquals(review.getReview(), "review1");

        Review review2 = reviewRowMapper.mapRow(resultSet, 0);
        Assert.assertEquals(review2.getId().longValue(), Long.MAX_VALUE);
        Assert.assertEquals(review2.getMovieId().longValue(), Long.MAX_VALUE);
        Assert.assertEquals(review2.getUserId().longValue(), Long.MAX_VALUE);
        Assert.assertEquals(review2.getReview(), "review2");
    }

    private void initLocalMocks() throws SQLException {
        Mockito.when(resultSet.getLong("id")).thenReturn(1L).thenReturn(Long.MAX_VALUE);
        Mockito.when(resultSet.getLong("movie_id")).thenReturn(1L).thenReturn(Long.MAX_VALUE);
        Mockito.when(resultSet.getLong("user_id")).thenReturn(1L).thenReturn(Long.MAX_VALUE);
        Mockito.when(resultSet.getString("review_c")).thenReturn("review1").thenReturn("review2");
    }
}
