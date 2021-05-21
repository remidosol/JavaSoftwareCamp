package kodlamaio.homework6.business.concretes;

import kodlamaio.homework6.business.abstracts.EmployerService;
import kodlamaio.homework6.dataAccess.abstracts.EmployerDao;
import kodlamaio.homework6.dataAccess.abstracts.UserDao;
import kodlamaio.homework6.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    EmployerDao employerDao;

    @Autowired
    public EmployerManager(EmployerDao employerDao) {
        this.employerDao = employerDao;
    }

    @Override
    public List<Employer> getAll() {
        return this.employerDao.findAll();
    }
}
