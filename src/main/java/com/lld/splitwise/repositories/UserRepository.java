package com.lld.splitwise.repositories;

import com.lld.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsernameEqualsOrPhoneNumber(String username, String phoneNumber);
}
