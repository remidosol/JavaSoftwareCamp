package kodlamaio.homework6.business.abstracts;


import kodlamaio.homework6.entities.abstracts.Result;
import kodlamaio.homework6.entities.concretes.JobSeeker;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface JobSeekerService {

    List<JobSeeker> fetch();

    Serializable create(JobSeeker jobSeeker);

    Optional<JobSeeker> findById(int id);

    Optional<List<JobSeeker>> findByFirstName(String firstName);

    Optional<List<JobSeeker>> findByLastName(String lastName);

    Result CheckIfYoungerAgeThan(JobSeeker jobSeeker, int age);
}
