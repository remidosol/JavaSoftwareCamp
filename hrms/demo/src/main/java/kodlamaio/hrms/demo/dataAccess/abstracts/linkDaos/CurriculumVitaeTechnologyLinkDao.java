package kodlamaio.hrms.demo.dataAccess.abstracts.linkDaos;

import kodlamaio.hrms.demo.entities.concretes.links.CurriculumVitaeTechnologyLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumVitaeTechnologyLinkDao extends JpaRepository<CurriculumVitaeTechnologyLink, Integer> {
}
