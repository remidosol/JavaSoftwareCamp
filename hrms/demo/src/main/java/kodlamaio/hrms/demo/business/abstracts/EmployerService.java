package kodlamaio.hrms.demo.business.abstracts;

import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.entities.concretes.Employer;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;

import java.util.List;

public interface EmployerService {
    DataResult<List<Employer>> getAll();
}
