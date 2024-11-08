package com.devkduck.letterfromthesea.user.domain;

import lombok.Data;

@Data
public class Userdto {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String roles;
}
