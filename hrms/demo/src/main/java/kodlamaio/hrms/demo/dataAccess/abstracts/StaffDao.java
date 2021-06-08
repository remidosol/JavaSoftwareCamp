package kodlamaio.hrms.demo.dataAccess.abstracts;

import kodlamaio.hrms.demo.entities.concretes.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StaffDao extends JpaRepository<Staff, Integer> {

    @Query("SELECT t FROM Staff t WHERE t.firstName = ?1")
    Optional<List<Staff>> findByFirstNameIgnoreCase(String firstName);

    @Query("SELECT t FROM Staff t WHERE t.lastName = ?1")
    Optional<List<Staff>> findByLastNameIgnoreCase(String lastName);

    @Query("SELECT t FROM Staff t WHERE t.id = ?1")
    Optional<Staff> findById(Long id);
}
