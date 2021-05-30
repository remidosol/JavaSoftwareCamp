package kodlamaio.hrms.demo.business.concretes;

import kodlamaio.hrms.demo.business.abstracts.StaffService;
import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.core.utilities.results.Result;
import kodlamaio.hrms.demo.core.utilities.results.SuccessResult;
import kodlamaio.hrms.demo.dataAccess.abstracts.StaffDao;
import kodlamaio.hrms.demo.entities.concretes.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class StaffManager implements StaffService {

    StaffDao staffDao;

    @Autowired
    public StaffManager(StaffDao staffDao) {
        this.staffDao = staffDao;
    }

    @Override
    public DataResult<List<Staff>> fetch() {

        return new DataResult<List<Staff>>(this.staffDao.findAll(), true);
    }

    @Override
    public Result create(Staff staff) {
        this.staffDao.save(staff);

        return new SuccessResult("Staff member has been created.");
    }

    @Override
    public DataResult<Optional<Staff>> findById(Long id) {

        return new DataResult<Optional<Staff>>(this.staffDao.findById(id), true);
    }

    @Override
    public DataResult<Optional<List<Staff>>> findByFirstNameIgnoreCase(String firstName) {
        return new DataResult<Optional<List<Staff>>>(this.staffDao.findByFirstNameIgnoreCase(firstName), true);
    }

    @Override
    public DataResult<Optional<List<Staff>>> findByLastNameIgnoreCase(String lastName) {
        return new DataResult<Optional<List<Staff>>>(this.staffDao.findByLastNameIgnoreCase(lastName), true);
    }
}
