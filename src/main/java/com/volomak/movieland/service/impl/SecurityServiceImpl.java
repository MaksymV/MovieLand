package com.volomak.movieland.service.impl;

import com.volomak.movieland.controller.error.IncorrectCredentials;
import com.volomak.movieland.entity.UserToken;
import com.volomak.movieland.service.SecurityService;
import com.volomak.movieland.service.UserService;
import com.volomak.movieland.service.dto.UserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Override
    public UserToken authorize(UserCredentials userCredentials){
        log.info("Start check credentials");
        long startTime = System.currentTimeMillis();
        UserToken userToken;
        try {
            userToken = new UserToken(userService.getByCredentials(userCredentials));
        } catch (EmptyResultDataAccessException e) {
            throw new IncorrectCredentials(e);
        }
        log.info("Finish check credentials. It took {} ms", System.currentTimeMillis() - startTime);
        return userToken;
    }
}
