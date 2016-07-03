package com.volomak.movieland.service;

import com.volomak.movieland.entity.UserToken;
import com.volomak.movieland.service.dto.UserCredentials;
import org.springframework.stereotype.Service;

@Service
public interface SecurityService {

        UserToken authorize(UserCredentials userCredentials);
}
