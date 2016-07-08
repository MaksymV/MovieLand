package com.volomak.movieland.service;

import com.volomak.movieland.entity.UserToken;
import com.volomak.movieland.service.dto.UserCredentials;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface SecurityService {

        UserToken authorize(UserCredentials userCredentials);

        public UserToken getByToken(UUID token);
}
