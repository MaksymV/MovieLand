package com.volomak.movieland.util;

import com.volomak.movieland.entity.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MovieComparatorTest {

    private static List<Movie> movies;

    private static Movie movie1;
    private static Movie movie2;
    private static Movie movie3;
    private static Movie movie4;


    @BeforeClass
    public static void setUpBeforeClass(){
        movie1 = new Movie();
        movie1.setId(1L);
        movie1.setRate(7.0);
        movie1.setPrice(8.1);

        movie2 = new Movie();
        movie2.setId(2L);
        movie2.setRate(4.4);
        movie2.setPrice(9.2);

        movie3 = new Movie();
        movie3.setId(3L);
        movie3.setRate(4.4);
        movie3.setPrice(8.2);

        movie4 = new Movie();
        movie4.setId(4L);
        movie4.setRate(2.5);
        movie4.setPrice(9.8);
    }

    @Before
    public void setUp(){
        movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
    }

    @Test
    public void testTest(){
        Assert.assertTrue(true);
    }

    @Test
    public void testComparatorAscAsc(){
        movies.sort(new MovieComparator("asc", "asc"));
        Assert.assertEquals(movie4, movies.get(0));
        Assert.assertEquals(movie3, movies.get(1));
        Assert.assertEquals(movie2, movies.get(2));
        Assert.assertEquals(movie1, movies.get(3));

    }

    @Test
    public void testComparatorAscDesc(){
        movies.sort(new MovieComparator("asc", "desc"));
        Assert.assertEquals(movie4, movies.get(0));
        Assert.assertEquals(movie2, movies.get(1));
        Assert.assertEquals(movie3, movies.get(2));
        Assert.assertEquals(movie1, movies.get(3));

    }

    @Test
    public void testComparatorDescAsc(){
        movies.sort(new MovieComparator("desc", "asc"));
        Assert.assertEquals(movie1, movies.get(0));
        Assert.assertEquals(movie3, movies.get(1));
        Assert.assertEquals(movie2, movies.get(2));
        Assert.assertEquals(movie4, movies.get(3));

    }

    @Test
    public void testComparatorDescDesc(){
        movies.sort(new MovieComparator("desc", "desc"));
        Assert.assertEquals(movie1, movies.get(0));
        Assert.assertEquals(movie2, movies.get(1));
        Assert.assertEquals(movie3, movies.get(2));
        Assert.assertEquals(movie4, movies.get(3));

    }

    @Test
    public void testComparatorNullAsc(){
        movies.sort(new MovieComparator("", "asc"));
        Assert.assertEquals(movie1, movies.get(0));
        Assert.assertEquals(movie3, movies.get(1));
        Assert.assertEquals(movie2, movies.get(2));
        Assert.assertEquals(movie4, movies.get(3));

    }


    @Test
    public void testComparatorNullDesc(){
        movies.sort(new MovieComparator("", "desc"));
        Assert.assertEquals(movie4, movies.get(0));
        Assert.assertEquals(movie2, movies.get(1));
        Assert.assertEquals(movie3, movies.get(2));
        Assert.assertEquals(movie1, movies.get(3));

    }

}
