package kodlamaio.hrms.demo.dataAccess.abstracts;

import kodlamaio.hrms.demo.entities.concretes.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityDao extends JpaRepository<University, Integer> {
}
