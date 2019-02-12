package com.travelblog.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentsError {
    private String errorMessage;
}
