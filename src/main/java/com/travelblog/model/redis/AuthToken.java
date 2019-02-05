package com.travelblog.model.redis;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash("AuthToken")
public class AuthToken {
    @Id
    private String token;
}
