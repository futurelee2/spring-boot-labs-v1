package com.captainyun7.postappwithsecurity.repository.user;

import com.captainyun7.postappwithsecurity.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,String> {
}

