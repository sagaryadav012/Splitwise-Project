package com.lld.splitwise.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class User extends BaseModel{
    private String username;
    private String phoneNumber;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                "userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
