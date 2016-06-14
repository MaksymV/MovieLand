package com.volomak.movieland.service;

import com.volomak.movieland.dao.MovieDao;
import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.service.dto.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by grey4 on 14.06.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/root-context.xml")
@WebAppConfiguration
public class MovieServiceTest {

    @Mock
    private MovieDao movieDao;

    @Mock
    private GenreService genreService;

    @Mock
    private CountryService countryService;

    @Mock
    private ReviewService reviewService;

    @Autowired
    @InjectMocks
    private MovieService movieService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        initLocalMocks();
    }

    @Test
    public void testTest() {
        Assert.assertTrue(true);
    }

    @Test
    public void getMoviesTest(){
        List<MovieListDto> movieListDtoList = movieService.getMovies();
        MovieListDto movieListDto1 = movieListDtoList.get(0);
        Assert.assertEquals(movieListDto1.getName(),"кино1");
        List<GenreListDto> genreListDtoList1 = movieListDto1.getGenres();
        Assert.assertEquals(genreListDtoList1.get(0).getName(),"genre1");
        Assert.assertEquals(genreListDtoList1.get(1).getName(),"genre2");
    }

    @Test
    public void getById(){
        MovieDetailsDto movieDetailsDto1 = movieService.getById(1L);
        List<CountryListDto> countryListDtoList1 = movieDetailsDto1.getCountries();
        Assert.assertEquals(countryListDtoList1.get(0).getName(),"country1");
        Assert.assertEquals(countryListDtoList1.get(1).getName(),"country2");
        List<ReviewListDto> reviewListDtoList1 = movieDetailsDto1.getReviews();
        Assert.assertEquals(reviewListDtoList1.get(0).getReview(),"review1");
        Assert.assertEquals(reviewListDtoList1.get(1).getReview(),"review2");

    }

    private void initLocalMocks() {

        CountryListDto country1 = new CountryListDto();
        country1.setName("country1");
        CountryListDto country2 = new CountryListDto();
        country2.setName("country2");
        CountryListDto country3 = new CountryListDto();
        country3.setName("country3");

        List<CountryListDto> countries1 = new ArrayList<>(Arrays.asList(country1, country2));
        List<CountryListDto> countries2 = new ArrayList<>(Arrays.asList(country2, country3));

        GenreListDto genre1 = new GenreListDto();
        genre1.setName("genre1");
        GenreListDto genre2 = new GenreListDto();
        genre2.setName("genre2");
        GenreListDto genre3 = new GenreListDto();
        genre3.setName("genre3");

        List<GenreListDto> genres1 = new ArrayList<>(Arrays.asList(genre1, genre2));
        List<GenreListDto> genres2 = new ArrayList<>(Arrays.asList(genre2, genre3));

        ReviewListDto review1 = new ReviewListDto();
        review1.setReview("review1");
        ReviewListDto review2 = new ReviewListDto();
        review2.setReview("review2");
        ReviewListDto review3 = new ReviewListDto();
        review3.setReview("review3");

        List<ReviewListDto> reviews1 = new ArrayList<>(Arrays.asList(review1, review2));
        List<ReviewListDto> reviews2 = new ArrayList<>(Arrays.asList(review2, review3));

        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setName("кино1");
        movie1.setOriginalName("movie1");
        movie1.setDescription("description1");
        movie1.setYear(2001);
        movie1.setPrice(10.1d);
        movie1.setRate(7.6d);
        Movie movie2 = new Movie();
        movie2.setId(2L);
        movie2.setName("кино2");
        movie2.setOriginalName("movie2");
        movie2.setDescription("description2");
        movie2.setYear(2002);
        movie2.setPrice(9.5d);
        movie2.setRate(8.1d);

        List<Movie> movies1 = new ArrayList<>(Arrays.asList(movie1, movie2));

        Mockito.when(movieDao.getMovies()).thenReturn(movies1);
        Mockito.when(movieDao.getById(1L)).thenReturn(movie1);
        Mockito.when(genreService.getByMovieId(1L)).thenReturn(genres1);
        Mockito.when(genreService.getByMovieId(2L)).thenReturn(genres2);
        Mockito.when(countryService.getByMovieId(1L)).thenReturn(countries1);
        Mockito.when(countryService.getByMovieId(2L)).thenReturn(countries2);
        Mockito.when(reviewService.getByMovieId(1L)).thenReturn(reviews1);
        Mockito.when(reviewService.getByMovieId(2L)).thenReturn(reviews2);


    }
}
