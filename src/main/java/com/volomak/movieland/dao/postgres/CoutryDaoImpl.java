package com.volomak.movieland.dao.postgres;

import com.volomak.movieland.dao.CountryDao;
import com.volomak.movieland.dao.postgres.mapper.CountryRowMapper;
import com.volomak.movieland.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoutryDaoImpl implements CountryDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final CountryRowMapper countryRowMapper = new CountryRowMapper();

    @Autowired
    private String getCountriesByMovieIdSQL;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Country> getByMovieId(Long id) {
        log.info("Start query to get countris with movie id {} from DB", id);
        long startTime = System.currentTimeMillis();
        List<Country> countries =  jdbcTemplate.query(getCountriesByMovieIdSQL, new Object[]{id}, countryRowMapper);
        log.info("Finish query to get countries with movie id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return countries;
    }
}
