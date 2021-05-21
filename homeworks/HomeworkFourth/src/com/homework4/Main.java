package com.homework4;

import com.homework4.business.concretes.GamerManager;
import com.homework4.business.concretes.UserManager;
import com.homework4.core.ValidatorManagerAdapter;
import com.homework4.dataAccess.abstracts.GamerDao;
import com.homework4.dataAccess.abstracts.UserDao;
import com.homework4.dataAccess.concretes.HibernateGamerDao;
import com.homework4.dataAccess.concretes.HibernateUserDao;
import com.homework4.entities.concretes.Gamer;
import com.homework4.entities.concretes.User;
import com.homework4.simulatedValidator.ValidatorManager;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

//        UserDao userDao = new HibernateUserDao();
//        User user = new User(1, "gamer@email.com", "pass123");
//        UserManager userManager = new UserManager(userDao);
//        userManager.create(user);
//
//        GamerDao gamerDao = new HibernateGamerDao();
//        Gamer gamer = new Gamer( 1, "asdada@asdkad.com", "pass123",
//                "John", "Doe", "21547895463", 1997);
//        GamerManager gamerManager = new GamerManager(gamerDao, new ValidatorManagerAdapter());
//        gamerManager.create(gamer);

        // AND OTHER STUFF etc.
    }
}
