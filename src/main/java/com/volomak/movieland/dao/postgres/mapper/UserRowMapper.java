package com.volomak.movieland.dao.postgres.mapper;

import com.volomak.movieland.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;



public class UserRowMapper implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name_c"));
        user.setEmail(resultSet.getString("email_c"));
        user.setPassword(resultSet.getString("password_c"));
        user.setRole(resultSet.getString("role_c"));
        return user;
    }
}
