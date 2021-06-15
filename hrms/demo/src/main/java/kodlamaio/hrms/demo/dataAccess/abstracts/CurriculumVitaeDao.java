package kodlamaio.hrms.demo.dataAccess.abstracts;

import kodlamaio.hrms.demo.entities.concretes.CurriculumVitae;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumVitaeDao extends JpaRepository<CurriculumVitae, Integer> {

    @Query("SELECT c FROM CurriculumVitae c WHERE EXISTS (SELECT j FROM JobSeeker j WHERE j.id = ?1)")
    CurriculumVitae getByJobSeekerId_JobSeekerId(Long JobSeekerId);
}
