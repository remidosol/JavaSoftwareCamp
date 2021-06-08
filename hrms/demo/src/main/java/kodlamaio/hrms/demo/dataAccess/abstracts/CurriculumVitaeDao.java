package kodlamaio.hrms.demo.dataAccess.abstracts;

import kodlamaio.hrms.demo.entities.concretes.CurriculumVitae;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumVitaeDao extends JpaRepository<CurriculumVitae, Integer> {

    CurriculumVitae getByJobSeekerId_JobSeekerId(Long JobSeekerId);
}
