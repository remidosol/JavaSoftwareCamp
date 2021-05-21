package kodlamaio.homework6.dataAccess.abstracts;

import kodlamaio.homework6.entities.concretes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerDao  extends JpaRepository<Employer, Integer> {
}
