package com.haedong.backend.auth;

import lombok.Getter;

@Getter
public class User {
    private String id;
    private String username;
    private String password;
    private String role;
    private Long createdAt;
    private Long updatedAt;
    private String updatedBy;
}
