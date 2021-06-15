package kodlamaio.hrms.demo.dataAccess.abstracts;

import kodlamaio.hrms.demo.entities.concretes.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementDao extends JpaRepository<Advertisement, Integer> {

    @Query("SELECT t FROM Advertisement t WHERE t.id = ?1")
    Advertisement getById(Long id);

    @Query("SELECT t FROM Advertisement t WHERE t.isActive = true")
    List<Advertisement> getAllActive();

    @Query("SELECT t FROM Advertisement t WHERE t.employer.id = ?1")
    List<Advertisement> getByEmployer_EmployerId(Long employerId);
}
