package com.lld.splitwise.services;

import com.lld.splitwise.exceptions.RegisterUserException;
import com.lld.splitwise.models.User;
import com.lld.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String username, String password, String phoneNumber) throws RegisterUserException {
        Optional<User> existingUser = userRepository.findUserByUsernameEqualsOrPhoneNumber(username, phoneNumber);
        if(existingUser.isPresent()){
            throw new RegisterUserException("User already registered!");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);

        return this.userRepository.save(user);
    }
}
