package com.travelblog.repository.redis;

import com.travelblog.model.redis.AuthToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthTokenRepository extends CrudRepository<AuthToken, String> {

    Optional<AuthToken> findOneByToken(String token);

}
