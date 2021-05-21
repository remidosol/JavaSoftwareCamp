package kodlamaio.homework6.business.abstracts;

import kodlamaio.homework6.entities.concretes.JobPosition;
import kodlamaio.homework6.entities.concretes.JobSeeker;

import java.util.List;

public interface JobPositionService {
    List<JobPosition> getAll();
}
