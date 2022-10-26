package com.ottplatform.service;

import java.util.Optional;

import com.ottplatform.entities.RefreshToken;

public interface RefreshTokenService {

    public RefreshToken createRefreshToken(Long userId) throws Exception;

    public boolean verifyExpiration(RefreshToken token) throws Exception;

    public Optional<RefreshToken> findByToken(String refreshToken);
}
