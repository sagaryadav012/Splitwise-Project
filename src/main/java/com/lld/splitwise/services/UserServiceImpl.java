package com.lld.splitwise.services;

import com.lld.splitwise.exceptions.InvalidRequestException;
import com.lld.splitwise.exceptions.RegisterUserException;
import com.lld.splitwise.models.User;
import com.lld.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPassword = encoder.encode(password);
        user.setPassword(encoderPassword);
        user.setPhoneNumber(phoneNumber);

        return this.userRepository.save(user);
    }
    public void login(String userName, String password) throws InvalidRequestException {
//        User user = this.userRepository.findUserByUserName(userName).orElseThrow(() -> new InvalidRequestException("Invalid user"));
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if(encoder.matches(password, user.getPassword())){
//            // login the user
//        } else {
//            // throw exception
//        }
    }
}
