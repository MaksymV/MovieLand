package com.volomak.movieland.controller;

import com.volomak.movieland.entity.UserToken;
import com.volomak.movieland.service.SecurityService;
import com.volomak.movieland.service.dto.UserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/v1/user")
public class AuthorizationController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public UserToken authorize(@RequestBody UserCredentials userCredentials){
        log.info("Start authorization user {}", userCredentials.getLogin());
        long startTime = System.currentTimeMillis();
        UserToken userToken = securityService.authorize(userCredentials);
        log.info("Finish authorization user {}. It took {} ms" ,userCredentials.getLogin(), System.currentTimeMillis() - startTime);
        return userToken;
    }


}
