package com.haedong.backend.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotBlank
    private String id;
    @NotBlank
    private String password;
}
