package com.travelblog.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CredentialsDTO {
    private String email;
    private String password;
}
