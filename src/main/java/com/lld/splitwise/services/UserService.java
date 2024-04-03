package com.lld.splitwise.services;

import com.lld.splitwise.exceptions.RegisterUserException;
import com.lld.splitwise.models.User;

public interface UserService {
    User registerUser(String username, String password, String phoneNumber) throws RegisterUserException;
}
