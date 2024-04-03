package com.lld.splitwise.controllers;

import com.lld.splitwise.dtos.RegisterUserRequestDto;
import com.lld.splitwise.dtos.RegisterUserResponseDto;
import com.lld.splitwise.dtos.Response;
import com.lld.splitwise.exceptions.InvalidRequestException;
import com.lld.splitwise.models.User;
import com.lld.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public RegisterUserResponseDto registerUser(RegisterUserRequestDto requestDto){
        RegisterUserResponseDto responseDto = new RegisterUserResponseDto();
        try {
            validateRequest(requestDto);
            User user = this.userService.registerUser(requestDto.getUsername(), requestDto.getPassword(), requestDto.getPhoneNumber());
            responseDto.setUser(user);
            responseDto.setResponse(Response.getSuccessResponse());

        } catch (Exception e) {
            responseDto.setResponse(Response.getFailureResponse(e.getMessage()));
        }
        return responseDto;
    }

    private void validateRequest(RegisterUserRequestDto requestDto) throws InvalidRequestException{
        if(requestDto.getUsername() == null || requestDto.getPassword() == null ||
            requestDto.getPhoneNumber() == null){
            throw new InvalidRequestException("Username or Password or PhoneNumber should not be null");
        }
    }
}
