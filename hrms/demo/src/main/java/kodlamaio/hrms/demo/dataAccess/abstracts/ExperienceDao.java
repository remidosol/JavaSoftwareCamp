package kodlamaio.hrms.demo.dataAccess.abstracts;

import kodlamaio.hrms.demo.entities.concretes.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceDao extends JpaRepository<Experience, Integer> {
}
