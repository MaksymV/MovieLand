package com.volomak.movieland.service.cache.impl;

import com.volomak.movieland.entity.UserToken;
import com.volomak.movieland.service.cache.UserTokenCache;
import com.volomak.movieland.service.dto.UserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserTokenCacheImpl implements UserTokenCache {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final int FIXED_DELAY = 60*60*1000;
    private Map<String, UserToken> userTokenMap = new ConcurrentHashMap<>();

    @Override
    public UserToken add(UserToken userToken) {
        log.info("Start add UserToken for {}", userToken.getLogin());
        long startTime = System.currentTimeMillis();
        userTokenMap.put(userToken.getLogin(), userToken);
        log.info("add UserToken for {}. It took {} ms", userToken.getLogin(), System.currentTimeMillis() - startTime);
        return userToken;
    }

    @Override
    public UserToken get(UserCredentials userCredentials) {
        log.info("Start get UserToken");
        long startTime = System.currentTimeMillis();
        UserToken userToken = userTokenMap.get(userCredentials.getLogin());
        log.info("Finish get UserToken. It took {} ms", System.currentTimeMillis() - startTime);
        return userToken;
    }

    @Override
    public boolean isExist(UserCredentials userCredentials) {
        if (userTokenMap.containsKey(userCredentials.getLogin())){
            if (isUserTokenExpired(userTokenMap.get(userCredentials.getLogin()).getExpirationDate())){
                userTokenMap.remove(userCredentials.getLogin());
                return false;
            } else return true;
        }
        return false;
    }

    @Override
    public boolean validate(UserCredentials userCredentials, UUID token) {
        UserToken userToken = userTokenMap.get(userCredentials.getLogin());
        if (userToken == null){
            return false;
        }
        return userToken.getToken().equals(token);
    }


    @Scheduled(fixedDelay = FIXED_DELAY)
    public void purgeExpiredToken(){
        log.info("Start purge cache of UserToke");
        long startTime = System.currentTimeMillis();
        for (Iterator<Map.Entry<String, UserToken>> it = userTokenMap.entrySet().iterator(); it.hasNext(); ){
            Map.Entry<String, UserToken> entry = it.next();
            if(isUserTokenExpired(entry.getValue().getExpirationDate())){
                it.remove();
            }
        }

        log.info("Finish purge cache of UserToke. It took {} ms", System.currentTimeMillis() - startTime);
    }

    private boolean isUserTokenExpired(LocalDateTime date){
        return LocalDateTime.now().isAfter(date);
    }
}
