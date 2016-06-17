package com.volomak.movieland.dao.postgres;

import com.volomak.movieland.dao.GenreDao;
import com.volomak.movieland.dao.postgres.mapper.GenreRowMapper;
import com.volomak.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreDaoImpl implements GenreDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getGenreByIdSQL;

    @Autowired
    private String getGenresByMovieIdSQL;

    @Override
    public Genre getById(Long id) {
        log.info("Start query to get genre with id {} from DB", id);
        long startTime = System.currentTimeMillis();
        Genre genre = jdbcTemplate.queryForObject(getGenreByIdSQL, new Object[]{id}, new GenreRowMapper());
        log.info("Finish query to get genre with id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return genre;
    }

    @Override
    public List<Genre> getByMovieId(Long id) {
        log.info("Start query to get genres with movie id {} from DB", id);
        long startTime = System.currentTimeMillis();
        List<Genre> genres = jdbcTemplate.query(getGenresByMovieIdSQL, new Object[]{id}, new GenreRowMapper());
        log.info("Finish query to get genres with movie id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return genres;
    }
}
