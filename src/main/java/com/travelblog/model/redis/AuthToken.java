package com.travelblog.model.redis;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("AuthToken")
public class AuthToken {
    @Id
    @Getter
    private String token;
}
