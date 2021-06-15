package kodlamaio.hrms.demo.dataAccess.abstracts.linkDaos;

import kodlamaio.hrms.demo.entities.concretes.links.CurriculumVitaeLanguageLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumVitaeLanguageLinkDao extends JpaRepository<CurriculumVitaeLanguageLink, Integer> {
}
