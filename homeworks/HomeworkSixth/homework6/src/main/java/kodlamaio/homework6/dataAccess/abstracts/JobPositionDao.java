package kodlamaio.homework6.dataAccess.abstracts;

import kodlamaio.homework6.entities.concretes.JobPosition;
import kodlamaio.homework6.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPositionDao extends JpaRepository<JobPosition, Integer> {
}
