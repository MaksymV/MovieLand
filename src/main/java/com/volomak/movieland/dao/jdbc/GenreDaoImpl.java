package com.volomak.movieland.dao.jdbc;

import com.volomak.movieland.dao.GenreDao;
import com.volomak.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.volomak.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class GenreDaoImpl implements GenreDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    private String getGenreByIdSQL;

    @Autowired
    private String addGenreSQL;

    @Override
    public Genre getById(int id) {
        log.info("Start getById genre");
        long startTime = System.currentTimeMillis();
        Genre genre = jdbcTemplate.queryForObject(getGenreByIdSQL, new Object[]{id}, new GenreRowMapper());
        log.info("Finish getById");
        return genre;
    }

    @Override
    public void add(Genre genre) {
        log.info("Start add genre");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", genre.getId());
        parameterSource.addValue("name_c", genre.getName());
        namedJdbcTemplate.update(addGenreSQL, parameterSource);
        log.info("Finish add genre");
    }
}
