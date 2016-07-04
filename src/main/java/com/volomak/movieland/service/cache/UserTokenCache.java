package com.volomak.movieland.service.cache;

import com.volomak.movieland.entity.UserToken;
import com.volomak.movieland.service.dto.UserCredentials;

import java.util.UUID;

public interface UserTokenCache {
    UserToken add(UserToken userToken);

    UserToken get(UserCredentials userCredentials);

    boolean isExist(UserCredentials userCredentials);

    boolean validate(UserCredentials userCredentials, UUID token);
}
