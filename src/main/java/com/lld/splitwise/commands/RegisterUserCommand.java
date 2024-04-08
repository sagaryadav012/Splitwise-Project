package com.lld.splitwise.commands;

import com.lld.splitwise.controllers.SettleUpController;
import com.lld.splitwise.controllers.UserController;
import com.lld.splitwise.dtos.*;
import com.lld.splitwise.exceptions.InvalidCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserCommand implements Command{
    UserController userController;
    private static final String REGISTER_USER_KEY = "Register";

    @Autowired
    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
        CommandRegistry.getInstance().register(REGISTER_USER_KEY, this);
    }

    @Override
    public void execute(String input) throws InvalidCommandException {
        //eg input : Register vinsmokesanji 003 namisswwaann

        String[] s = input.split(" ");
        if(s.length != 4){
            throw new InvalidCommandException("Register user command not in correct format");
        }
        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUsername(s[1]);
        requestDto.setPassword(s[2]);
        requestDto.setPhoneNumber(s[3]);

        RegisterUserResponseDto responseDto = userController.registerUser(requestDto);

        if(responseDto.getResponse().getResponseStatus().equals(ResponseStatus.FAILURE)){
            System.out.println("Error : "+ responseDto.getResponse().getError_msg());
        }
        else{
            System.out.println(responseDto.getUser());
        }
    }
}
