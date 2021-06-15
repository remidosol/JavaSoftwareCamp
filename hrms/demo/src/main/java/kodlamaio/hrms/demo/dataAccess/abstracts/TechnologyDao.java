package kodlamaio.hrms.demo.dataAccess.abstracts;

import kodlamaio.hrms.demo.entities.concretes.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyDao extends JpaRepository<Technology, Integer> {
}
