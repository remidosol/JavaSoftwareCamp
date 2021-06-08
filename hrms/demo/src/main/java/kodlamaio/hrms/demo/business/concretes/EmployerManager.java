package kodlamaio.hrms.demo.business.concretes;

import kodlamaio.hrms.demo.business.abstracts.EmployerService;
import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.demo.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.demo.entities.concretes.Employer;
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
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "All of employers have been listed.");
    }
}
