package com.yahor.snow.additional;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginData {
    private String login;
    private String password;
}
