package com.volomak.movieland.dao.postgres.mapper;

import com.volomak.movieland.entity.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by grey4 on 16.06.2016.
 */
public class CountryRowMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country();
        country.setId(resultSet.getLong("id"));
        country.setName(resultSet.getString("name"));
        return country;
    }
}
