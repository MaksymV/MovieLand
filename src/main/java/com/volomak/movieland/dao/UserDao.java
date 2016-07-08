package com.volomak.movieland.dao;

import com.volomak.movieland.entity.User;
import com.volomak.movieland.service.dto.UserCredentials;

public interface UserDao {
    User getById(Long id);

    User getByName(String name);

    User getByCredentials(UserCredentials userCredentials);
}
