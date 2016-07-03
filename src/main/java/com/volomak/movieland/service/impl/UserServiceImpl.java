package com.volomak.movieland.service.impl;

import com.volomak.movieland.dao.UserDao;
import com.volomak.movieland.entity.User;
import com.volomak.movieland.service.UserService;
import com.volomak.movieland.service.dto.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public User getByCredentials(UserCredentials userCredentials) {
        return userDao.getByCredentials(userCredentials);
    }
}
