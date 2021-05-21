package com.homework4.business.abstracts;

import com.homework4.entities.concretes.User;

import java.util.List;

public interface UserService {

    void create(User user);
    void update(User user, int id, String email);
    void delete(User user);
    User get(int id);
    List<User> getAll();
}
