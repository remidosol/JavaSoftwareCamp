package kodlamaio.hrms.demo.business.abstracts;


import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.core.utilities.results.Result;
import kodlamaio.hrms.demo.entities.concretes.Advertisement;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;
import kodlamaio.hrms.demo.entities.concretes.User;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface JobSeekerService {

    DataResult<List<JobSeeker>> fetch();

    Result signUpAsJobSeeker(JobSeeker jobSeeker);

    DataResult<JobSeeker> findById(Long id);

    DataResult<JobSeeker> getByUserId_UserId(Long id);

    DataResult<List<JobSeeker>> findByFirstNameIgnoreCase(String firstName);

    DataResult<List<JobSeeker>> findByLastNameIgnoreCase(String lastName);

    Result CheckIfYoungerAgeThan(JobSeeker jobSeeker, int age);

    Result applyAnAdvertisement(Long jobSeekerId, Long advertisementId);

}
