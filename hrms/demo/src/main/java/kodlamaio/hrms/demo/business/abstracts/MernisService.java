package kodlamaio.hrms.demo.business.abstracts;

import kodlamaio.hrms.demo.core.utilities.results.Result;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;

public interface MernisService {
    Result CheckIfJobSeekerIsRealPerson(JobSeeker jobSeeker);
}
