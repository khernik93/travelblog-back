package com.travelblog.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostsError {
    private String errorMessage;
}
