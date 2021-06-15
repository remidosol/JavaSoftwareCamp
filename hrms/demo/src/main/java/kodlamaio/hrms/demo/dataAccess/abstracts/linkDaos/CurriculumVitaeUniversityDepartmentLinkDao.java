package kodlamaio.hrms.demo.dataAccess.abstracts.linkDaos;

import kodlamaio.hrms.demo.entities.concretes.links.CurriculumVitaeUniversityDepartmentLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumVitaeUniversityDepartmentLinkDao extends JpaRepository<CurriculumVitaeUniversityDepartmentLink, Integer> {
}
