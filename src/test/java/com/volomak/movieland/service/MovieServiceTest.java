package com.volomak.movieland.service;

import com.volomak.movieland.dao.MovieDao;
import com.volomak.movieland.entity.Country;
import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.entity.Movie;
import com.volomak.movieland.entity.Review;
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
        List<MovieListDto> movieListDtoList = movieService.getMovies("asc", "asc");
        MovieListDto movies1 = movieListDtoList.get(0);
        Assert.assertEquals(movies1.getName(),"кино1");
        List<Genre> genres1 = movies1.getGenres();
        Assert.assertEquals(genres1.get(0).getName(),"genre1");
        Assert.assertEquals(genres1.get(1).getName(),"genre2");
    }

    @Test
    public void getById(){
        MovieDetailsDto movieDetailsDto1 = movieService.getById(1L);
        List<Country> countryListDtoList1 = movieDetailsDto1.getCountries();
        Assert.assertEquals(countryListDtoList1.get(0).getName(),"country1");
        Assert.assertEquals(countryListDtoList1.get(1).getName(),"country2");
        List<Review> reviewListDtoList1 = movieDetailsDto1.getReviews();
        Assert.assertEquals(reviewListDtoList1.get(0).getReview(),"review1");
        Assert.assertEquals(reviewListDtoList1.get(1).getReview(),"review2");

    }

    private void initLocalMocks() {

        Country country1 = new Country();
        country1.setName("country1");
        Country country2 = new Country();
        country2.setName("country2");
        Country country3 = new Country();
        country3.setName("country3");

        List<Country> countries1 = new ArrayList<>(Arrays.asList(country1, country2));
        List<Country> countries2 = new ArrayList<>(Arrays.asList(country2, country3));

        Genre genre1 = new Genre();
        genre1.setName("genre1");
        Genre genre2 = new Genre();
        genre2.setName("genre2");
        Genre genre3 = new Genre();
        genre3.setName("genre3");

        List<Genre> genres1 = new ArrayList<>(Arrays.asList(genre1, genre2));
        List<Genre> genres2 = new ArrayList<>(Arrays.asList(genre2, genre3));

        Review review1 = new Review();
        review1.setReview("review1");
        Review review2 = new Review();
        review2.setReview("review2");
        Review review3 = new Review();
        review3.setReview("review3");

        List<Review> reviews1 = new ArrayList<>(Arrays.asList(review1, review2));
        List<Review> reviews2 = new ArrayList<>(Arrays.asList(review2, review3));

        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setName("кино1");
        movie1.setOriginalName("movie1");
        movie1.setDescription("description1");
        movie1.setYear(2001);
        movie1.setPrice(10.1d);
        movie1.setRate(7.6d);
        movie1.setGenres(genres1);
        movie1.setCountries(countries1);
        movie1.setReviews(reviews1);

        Movie movie2 = new Movie();
        movie2.setId(2L);
        movie2.setName("кино2");
        movie2.setOriginalName("movie2");
        movie2.setDescription("description2");
        movie2.setYear(2002);
        movie2.setPrice(9.5d);
        movie2.setRate(8.1d);
        movie2.setGenres(genres2);
        movie2.setCountries(countries2);
        movie2.setReviews(reviews2);

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
