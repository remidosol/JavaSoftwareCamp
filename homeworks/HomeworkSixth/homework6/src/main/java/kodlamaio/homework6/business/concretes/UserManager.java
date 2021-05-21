package kodlamaio.homework6.business.concretes;

import kodlamaio.homework6.business.abstracts.UserService;
import kodlamaio.homework6.dataAccess.abstracts.UserDao;
import kodlamaio.homework6.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManager implements UserService {

    UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> fetch() {
        return this.userDao.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userDao.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userDao.findByEmail(email);
    }
}
