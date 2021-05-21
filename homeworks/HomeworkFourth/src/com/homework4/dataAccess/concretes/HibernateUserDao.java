package com.homework4.dataAccess.concretes;

import com.homework4.dataAccess.abstracts.UserDao;
import com.homework4.entities.concretes.User;

import java.util.List;

public class HibernateUserDao implements UserDao {
    @Override
    public void create(User user) {
        System.out.println("User has been created " + user.getId() + " " + user.getEmail());
    }

    @Override
    public void update(User user, int id, String email) {
        user.setId(id);
        user.setEmail(email);
        System.out.println("User has been updated as " + user.getId() + user.getEmail());
    }

    @Override
    public void delete(User user) {
        System.out.println("User has been deleted.");
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
