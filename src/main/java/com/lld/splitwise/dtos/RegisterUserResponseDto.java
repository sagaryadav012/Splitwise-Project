package com.lld.splitwise.dtos;

import com.lld.splitwise.models.User;
import lombok.Data;

@Data
public class RegisterUserResponseDto {
    private Response response;
    private User user;
}
