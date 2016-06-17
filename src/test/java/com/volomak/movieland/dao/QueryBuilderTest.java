package com.volomak.movieland.dao;

import com.volomak.movieland.entity.Country;
import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.service.dto.MovieSearchRequestDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

/**
 * Created by grey4 on 17.06.2016.
 */
public class QueryBuilderTest {
    @Mock
    public MovieSearchRequestDto movieSearchRequestDto;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        initLocalMocks();
    }

    @Test
    public void movieSearch(){
        QueryBiulder queryBiulder = new QueryBiulder();
        String query = queryBiulder.movieSearch(movieSearchRequestDto);
        Assert.assertEquals("select", query);
    }

     private void initLocalMocks() {
         Mockito.when(movieSearchRequestDto.getName()).thenReturn("name1");
         Mockito.when(movieSearchRequestDto.getOriginalName()).thenReturn("name1");
         Mockito.when(movieSearchRequestDto.getYear()).thenReturn(2001);
         Country country = new Country();
         country.setId(1L);
         country.setName("country1");
         Mockito.when(movieSearchRequestDto.getCountry()).thenReturn(country);
         Genre genre = new Genre();
         genre.setId(1L);
         genre.setName("genre1");
         Mockito.when(movieSearchRequestDto.getGenre()).thenReturn(genre);
    }


}
