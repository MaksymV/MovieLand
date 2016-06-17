package com.volomak.movieland.dao;

import com.volomak.movieland.entity.User;

public interface UserDao {
    User getById(Long id);
}
