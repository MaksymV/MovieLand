package com.volomak.movieland.dao.jdbc;

import com.volomak.movieland.dao.CountryDao;
import com.volomak.movieland.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CoutryDaoImpl implements CountryDao {
    @Autowired
    private String getCountriesByMovieIdSQL;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Country getById(int id) {
        return null;
    }

    @Override
    public List<Country> getByMovieId(int id) {
        List<Country> countries =  jdbcTemplate.query(getCountriesByMovieIdSQL,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, id);
                    }
                },
                new RowMapper<Country>(){

                    @Override
                    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
                        Country country = new Country();
                        country.setName(resultSet.getString("name"));
                        return country;
                    }
                }
        );
        return countries;
    }

    @Override
    public void add(Country country) {

    }
}
