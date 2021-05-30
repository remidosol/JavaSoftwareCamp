package kodlamaio.hrms.demo.business.abstracts;

import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.entities.concretes.JobPosition;

import java.util.List;

public interface JobPositionService {
    DataResult<List<JobPosition>>  getAll();
}
