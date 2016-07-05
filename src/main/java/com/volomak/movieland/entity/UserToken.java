package com.volomak.movieland.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public final class UserToken {
    private static final Long USER_TOKEN_EXPIRATION = 2L;

    final private String login;
    final private UUID token;
    final private String role;
    final private LocalDateTime expirationDate;

    public UserToken(User user) {
        this.login = user.getName();
        this.token = UUID.randomUUID();
        this.role = user.getRole();
        this.expirationDate = LocalDateTime.now().plusHours(USER_TOKEN_EXPIRATION);
    }

    public String getLogin() {
        return login;
    }

    public UUID getToken() {
        return token;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public String getRole() {
        return role;
    }
}
