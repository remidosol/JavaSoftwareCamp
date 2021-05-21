package kodlamaio.homework6.dataAccess.abstracts;

import kodlamaio.homework6.entities.concretes.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobSeekerDao  extends JpaRepository<JobSeeker, Integer> {

    @Query("SELECT t FROM JobSeeker t WHERE t.firstName = ?1")
    Optional<List<JobSeeker>> findByFirstName(String firstName);

    @Query("SELECT t FROM JobSeeker t WHERE t.lastName = ?1")
    Optional<List<JobSeeker>> findByLastName(String lastName);
}
