package kodlamaio.hrms.demo.dataAccess.abstracts;

import kodlamaio.hrms.demo.entities.concretes.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdvertisementDao extends JpaRepository<Advertisement, Integer> {

    @Query("SELECT t FROM Advertisement t WHERE t.id = ?1")
    Optional<Advertisement> findById(Long id);
}