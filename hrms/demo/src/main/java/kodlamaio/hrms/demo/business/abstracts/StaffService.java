package kodlamaio.hrms.demo.business.abstracts;

import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.core.utilities.results.Result;
import kodlamaio.hrms.demo.entities.concretes.Staff;

import java.util.List;
import java.util.Optional;


public interface StaffService {

    DataResult<List<Staff>> fetch();

    Result create(Staff staff);

    DataResult<Optional<Staff>> findById(Long id);

    DataResult<Optional<List<Staff>>> findByFirstNameIgnoreCase(String firstName);

    DataResult<Optional<List<Staff>>> findByLastNameIgnoreCase(String lastName);
}
