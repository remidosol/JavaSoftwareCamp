package kodlamaio.homework6.api.databaseSeeders;

import com.github.javafaker.Faker;
import kodlamaio.homework6.dataAccess.abstracts.UserDao;
import kodlamaio.homework6.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {

    UserDao userDao;

    @Autowired
    public UserSeeder(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        Faker faker = new Faker();
        while(userDao.count() != 20){
            User user = new User();
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password(8,16));
            userDao.save(user);

        }
        System.out.println(userDao.count() + " users have been created!");

    }
}
