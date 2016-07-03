package com.volomak.movieland.dao.postgres;

import com.volomak.movieland.dao.UserDao;
import com.volomak.movieland.dao.postgres.mapper.UserRowMapper;
import com.volomak.movieland.entity.User;
import com.volomak.movieland.service.dto.UserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl implements UserDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final UserRowMapper userRowMapper = new UserRowMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getUserByName;

    @Autowired
    private String getUserById;

    @Autowired
    private String getUserByCredentials;

    @Override
    public User getById(Long id) {
        log.info("Start query to get user with user id {} from DB", id);
        long startTime = System.currentTimeMillis();
        User user = jdbcTemplate.queryForObject(getUserById, new Object[]{id}, userRowMapper);
        log.info("Finish query to get user with user id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return user;
    }

    @Override
    public User getByName(String name) {
        log.info("Start query to get user with user name {} from DB", name);
        long startTime = System.currentTimeMillis();
        User user = jdbcTemplate.queryForObject(getUserByName, new Object[]{name}, userRowMapper);
        log.info("Finish query to get user with user name {} from DB. It took {} ms", name, System.currentTimeMillis() - startTime);
        return user;
    }

    @Override
    public User getByCredentials(UserCredentials userCredentials) {
        log.info("Start query to get user with user name {} from DB", userCredentials.getLogin());
        long startTime = System.currentTimeMillis();
        User user = jdbcTemplate.queryForObject(getUserByCredentials, new Object[]{userCredentials.getLogin(), userCredentials.getPassword()}, userRowMapper);
        log.info("Finish query to get user with user name {} from DB. It took {} ms", userCredentials.getLogin(), System.currentTimeMillis() - startTime);
        return user;
    }
}
