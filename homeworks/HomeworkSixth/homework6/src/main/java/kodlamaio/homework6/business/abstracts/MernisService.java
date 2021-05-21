package kodlamaio.homework6.business.abstracts;

import kodlamaio.homework6.entities.concretes.JobSeeker;
import kodlamaio.homework6.entities.abstracts.Result;

public interface MernisService {
    Result CheckIfJobSeekerIsRealPerson(JobSeeker jobSeeker);
}
