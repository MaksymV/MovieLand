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
        String expectedResult;
        String query = queryBiulder.movieSearch(movieSearchRequestDto);
        expectedResult = "SELECT id, name, original_name, year_i, description_c, rate_r, price_r FROM movie\n"+
            "inner join movie_country on (movie_country.movie_id = movie.id) inner join country on (country.id = movie_country.country_id)\n"+
            "inner join movie_genre on (movie_genre.movie_id  = movie.id) inner join genre on (genre.id = movie_genre.genre_id)\n"+
            "where movie.name like '%name1%'\n"+
            "or movie.original_name like '%original_name1%'\n"+
            "or movie.year_i = 2001\n"+
            "or country.name like '%country1%'\n"+
            "or genre.name_c like '%genre1%'\n";
        Assert.assertEquals(expectedResult, query);

        query = queryBiulder.movieSearch(movieSearchRequestDto);
        expectedResult = "SELECT id, name, original_name, year_i, description_c, rate_r, price_r FROM movie\n"+
            "where movie.name like '%name2%'\n";
        Assert.assertEquals(expectedResult, query);

        query = queryBiulder.movieSearch(movieSearchRequestDto);
        expectedResult = "SELECT id, name, original_name, year_i, description_c, rate_r, price_r FROM movie\n"+
            "inner join movie_country on (movie_country.movie_id = movie.id) inner join country on (country.id = movie_country.country_id)\n"+
            "inner join movie_genre on (movie_genre.movie_id  = movie.id) inner join genre on (genre.id = movie_genre.genre_id)\n"+
            "where movie.name like '%name3%'\n"+
            "or country.name like '%country2%'\n"+
            "or genre.name_c like '%genre2%'\n";
        Assert.assertEquals(expectedResult, query);
    }

     private void initLocalMocks() {
         Mockito.when(movieSearchRequestDto.getName()).thenReturn("name1", "name1", "name2", "name2", "name3", "name3");
         Mockito.when(movieSearchRequestDto.getOriginalName()).thenReturn("original_name1", "original_name1", null);
         Mockito.when(movieSearchRequestDto.getYear()).thenReturn(2001, 2001, 0);
         Country country = new Country();
         country.setId(1L);
         country.setName("country1");
         Country country2 = new Country();
         country2.setId(2L);
         country2.setName("country2");
         Mockito.when(movieSearchRequestDto.getCountry()).thenReturn(country, country, null, country2, country2);
         Genre genre = new Genre();
         genre.setId(1L);
         genre.setName("genre1");
         Genre genre2 = new Genre();
         genre2.setId(1L);
         genre2.setName("genre2");
         Mockito.when(movieSearchRequestDto.getGenre()).thenReturn(genre, genre, null, genre2, genre2);
    }


}
