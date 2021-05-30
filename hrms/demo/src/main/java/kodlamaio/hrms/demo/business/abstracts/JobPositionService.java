package kodlamaio.hrms.demo.business.abstracts;

import kodlamaio.hrms.demo.entities.concretes.JobPosition;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;

import java.util.List;

public interface JobPositionService {
    List<JobPosition> getAll();
}
