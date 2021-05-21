package kodlamaio.homework6.business.abstracts;

import kodlamaio.homework6.entities.concretes.Employer;
import kodlamaio.homework6.entities.concretes.JobSeeker;

import java.util.List;

public interface EmployerService {
    List<Employer> getAll();
}
