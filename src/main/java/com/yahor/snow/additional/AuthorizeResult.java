package com.yahor.snow.additional;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizeResult {
    private boolean success;
    private String message;
    private String token;
}
