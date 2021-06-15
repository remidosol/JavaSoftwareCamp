package kodlamaio.hrms.demo.business.concretes;

import kodlamaio.hrms.demo.business.abstracts.UserService;
import kodlamaio.hrms.demo.core.dataAccess.UserDao;
import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.demo.dataAccess.abstracts.RoleDao;
import kodlamaio.hrms.demo.entities.concretes.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserManager implements UserService {

    private UserDao userDao;

    private JobSeekerDao jobSeekerDao;

    private RoleDao roleDao;

    private User user;

    @Autowired
    public UserManager(UserDao userDao, JobSeekerDao jobSeekerDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.jobSeekerDao = jobSeekerDao;
        this.roleDao = roleDao;
    }


    @Override
    public DataResult<List<User>> getAll() {
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
