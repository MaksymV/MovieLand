package com.volomak.movieland.dao.jdbc.mapper;

import com.volomak.movieland.entity.Genre;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapperTest {
    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        initLocalMocks();
    }

    @Test
    public void mapRowTest() throws SQLException {
        GenreRowMapper genreRowMapper = new GenreRowMapper();
        Genre genre = genreRowMapper.mapRow(resultSet, 0);
        Assert.assertEquals(genre.getId().longValue(), 1L);
        Assert.assertEquals(genre.getName(), "name1");
        Genre genre2 = genreRowMapper.mapRow(resultSet, 0);
        Assert.assertEquals(genre2.getId().longValue(), Long.MAX_VALUE);
        Assert.assertEquals(genre2.getName(), "name2");
    }

    private void initLocalMocks() throws SQLException {
        Mockito.when(resultSet.getLong("id")).thenReturn(1L).thenReturn(Long.MAX_VALUE);
        Mockito.when(resultSet.getString("name_c")).thenReturn("name1").thenReturn("name2");
    }
}
