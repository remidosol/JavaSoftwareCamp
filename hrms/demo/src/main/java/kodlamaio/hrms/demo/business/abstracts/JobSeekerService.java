package kodlamaio.hrms.demo.business.abstracts;


import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.core.utilities.results.Result;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;

import java.util.List;

public interface JobSeekerService {

    DataResult<List<JobSeeker>> getAll();

    Result signUpAsJobSeeker(JobSeeker jobSeeker);

    DataResult<JobSeeker> findById(Long id);

    DataResult<JobSeeker> getByUserId_UserId(Long id);

    DataResult<List<JobSeeker>> findByFirstNameIgnoreCase(String firstName);

    DataResult<List<JobSeeker>> findByLastNameIgnoreCase(String lastName);

    Result CheckIfYoungerAgeThan(JobSeeker jobSeeker, int age);

    Result applyAnAdvertisement(Long jobSeekerId, Long advertisementId);

}
