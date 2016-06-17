package com.volomak.movieland.dao.jdbc.mapper;

import com.volomak.movieland.entity.Country;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapperTest {
    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        initLocalMocks();
    }

    @Test
    public void mapRowTest() throws SQLException {
        CountryRowMapper countryRowMapper = new CountryRowMapper();
        Country country = countryRowMapper.mapRow(resultSet, 0);
        Assert.assertEquals(country.getId().intValue(), 1);
        Assert.assertEquals(country.getName(), "name1");
        Country country2 = countryRowMapper.mapRow(resultSet, 0);
        Assert.assertEquals(country2.getId().longValue(), Long.MAX_VALUE);
        Assert.assertEquals(country2.getName(), "name2");
    }

    private void initLocalMocks() throws SQLException {
        Mockito.when(resultSet.getLong("id")).thenReturn(1L).thenReturn(Long.MAX_VALUE);
        Mockito.when(resultSet.getString("name")).thenReturn("name1").thenReturn("name2");
    }
}
