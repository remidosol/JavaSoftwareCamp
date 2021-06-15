package kodlamaio.hrms.demo.dataAccess.abstracts;

import kodlamaio.hrms.demo.entities.concretes.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao extends JpaRepository<Department, Integer> {
}
