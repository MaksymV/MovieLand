package com.volomak.movieland.service;

import com.volomak.movieland.entity.User;
import com.volomak.movieland.service.dto.UserCredentials;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getById(Long id);

    User getByName(String name);

    User getByCredentials(UserCredentials userCredentials);
}
