package com.lld.splitwise.dtos;

import lombok.Data;

@Data
public class RegisterUserRequestDto {
    private String username;
    private String phoneNumber;
    private String password;
}
