package com.lld.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "group_s")
public class Group extends BaseModel{
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> admin;
}
