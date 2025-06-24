package com.captainyun7.postappwithsecurity.service;

import com.captainyun7.postappwithsecurity.domain.RefreshToken;
import com.captainyun7.postappwithsecurity.repository.user.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken createRefreshToken(RefreshToken refreshToken) {


        return refreshTokenRepository.save(refreshToken);
    }


}
