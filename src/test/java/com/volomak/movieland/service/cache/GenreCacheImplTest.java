package com.volomak.movieland.service.cache;

import com.volomak.movieland.entity.Genre;
import com.volomak.movieland.service.GenreService;
import com.volomak.movieland.service.cache.impl.GenreCacheImpl;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/root-context.xml")
@WebAppConfiguration
public class GenreCacheImplTest {

    @Mock
    private GenreService genreService;

    @Autowired
    @InjectMocks
    private GenreCacheImpl genreCache;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        initLocalMocks();
    }

    @Test
    public void test(){
        genreCache.cacheUpdate();
        List<Long> genresIds = new ArrayList<>(Arrays.asList(2L, 3L));
        List<Genre> genres =  genreCache.getGenres(genresIds);
        List<String> genreNames = new ArrayList<>();
        for (Genre genre : genres){
            genreNames.add(genre.getName());
        }
        Assert.assertTrue(genreNames.contains("name2"));
        Assert.assertTrue(genreNames.contains("name3"));
        Assert.assertFalse(genreNames.contains("name1"));
    }

    private void initLocalMocks() {
        Genre genre1 = new Genre(1L, "name1");
        Genre genre2 = new Genre(2L, "name2");
        Genre genre3 = new Genre(3L, "name3");

        List<Genre> genres = new ArrayList<>(Arrays.asList(genre1, genre2, genre3));

        Mockito.when(genreService.getGenres()).thenReturn(genres);
    }
}
