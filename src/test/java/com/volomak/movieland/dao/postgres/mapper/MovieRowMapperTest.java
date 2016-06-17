package com.volomak.movieland.dao.postgres.mapper;

import com.volomak.movieland.entity.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapperTest {

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        initLocalMocks();
    }

    @Test
    public void mapRowTest() throws SQLException {
        MovieRowMapper movieRowMapper = new MovieRowMapper();
        Movie movie = movieRowMapper.mapRow(resultSet, 0);
        Assert.assertEquals(movie.getId().intValue(), 1);
        Assert.assertEquals(movie.getName(), "name1");
        Assert.assertEquals(movie.getOriginalName(), "original_name1");
        Assert.assertEquals(movie.getYear(), 2001);
        Assert.assertEquals(movie.getDescription(), "description1");
        Assert.assertEquals(movie.getRate(), 2.2, 0.001);
        Assert.assertEquals(movie.getPrice(), 5.5, 0.001);

    }


    private void initLocalMocks() throws SQLException {
        Mockito.when(resultSet.getLong("id")).thenReturn(1L);
        Mockito.when(resultSet.getString("name")).thenReturn("name1");
        Mockito.when(resultSet.getString("original_name")).thenReturn("original_name1");
        Mockito.when(resultSet.getInt("year_i")).thenReturn(2001);
        Mockito.when(resultSet.getString("description_c")).thenReturn("description1");
        Mockito.when(resultSet.getDouble("rate_r")).thenReturn(2.2d);
        Mockito.when(resultSet.getDouble("price_r")).thenReturn(5.5d);
    }
}
