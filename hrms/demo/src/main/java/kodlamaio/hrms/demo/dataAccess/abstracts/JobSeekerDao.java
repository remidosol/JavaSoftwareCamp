package kodlamaio.hrms.demo.dataAccess.abstracts;

import kodlamaio.hrms.demo.entities.concretes.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer> {

    @Query("SELECT j FROM JobSeeker j WHERE j.firstName = ?1")
    List<JobSeeker> getByFirstNameIgnoreCase(String firstName);

    @Query("SELECT j FROM JobSeeker j WHERE j.lastName = ?1")
    List<JobSeeker> getByLastNameIgnoreCase(String lastName);

    @Query("SELECT j FROM JobSeeker j WHERE j.id = ?1")
    JobSeeker getById(Long id);

    @Query("SELECT j FROM JobSeeker j WHERE EXISTS (SELECT u FROM User u WHERE u.id = ?1)")
    JobSeeker getByUserId_UserId(Long userId);

//    @Query("SELECT new kodlamaio.hrms.demo.entities.dtos.UniversityWithDepartmentDto"
//            + "(j.id, u.name, d.name) "
//            + "FROM University u, Department d INNER JOIN JobSeeker j")
//    List<UniversityWithDepartmentDto> getUniversityWithDepartmentDetails();
}
