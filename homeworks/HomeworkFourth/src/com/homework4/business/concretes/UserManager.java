package com.homework4.business.concretes;

import com.homework4.business.abstracts.UserService;
import com.homework4.dataAccess.abstracts.UserDao;
import com.homework4.entities.concretes.User;

import java.util.List;

public class UserManager implements UserService {

    private UserDao userDao;

    public UserManager(UserDao userDao) {
        super();
        this.userDao = userDao;
    }

    @Override
    public void create(User user) {
        if (user.getEmail().isEmpty()){
            System.out.println("You can't create a user without an email!");
            return;
        } else if (user.getPassword().length() < 6){
            System.out.println("You must provide a password that char length greater or equal then 6.");
        }

        this.userDao.create(user);
    }

    @Override
    public void update(User user, int id, String email) {

    }

    @Override
    public void delete(User user) {

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
