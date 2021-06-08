package kodlamaio.hrms.demo.business.concretes;

import kodlamaio.hrms.demo.business.abstracts.UserService;
import kodlamaio.hrms.demo.core.dataAccess.UserDao;
import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.core.utilities.results.DataResult;
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
    public DataResult<List<User>> fetch() {
        return new DataResult<List<User>>(this.userDao.findAll(), true);
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
