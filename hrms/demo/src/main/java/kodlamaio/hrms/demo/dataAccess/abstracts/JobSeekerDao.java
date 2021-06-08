package kodlamaio.hrms.demo.dataAccess.abstracts;

import kodlamaio.hrms.demo.entities.concretes.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer> {

    @Query("SELECT t FROM JobSeeker t WHERE t.firstName = ?1")
    Optional<List<JobSeeker>> getByFirstNameIgnoreCase(String firstName);

    @Query("SELECT t FROM JobSeeker t WHERE t.lastName = ?1")
    Optional<List<JobSeeker>> getByLastNameIgnoreCase(String lastName);

    @Query("SELECT t FROM JobSeeker t WHERE t.id = ?1")
    Optional<JobSeeker> findById(Long id);

    Optional<JobSeeker> getByUserId_UserId(Long userId);

//    @Query("SELECT new kodlamaio.hrms.demo.entities.dtos.UniversityWithDepartmentDto"
//            + "(j.id, u.name, d.name) "
//            + "FROM University u, Department d INNER JOIN JobSeeker j")
//    List<UniversityWithDepartmentDto> getUniversityWithDepartmentDetails();
}
