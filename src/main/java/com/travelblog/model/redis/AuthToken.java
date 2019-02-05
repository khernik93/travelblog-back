package com.travelblog.model.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@AllArgsConstructor
@RedisHash("AuthToken")
public class AuthToken {
    @Id
    @Getter
    private String token;
}
