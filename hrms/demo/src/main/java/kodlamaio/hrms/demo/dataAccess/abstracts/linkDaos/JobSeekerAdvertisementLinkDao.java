package kodlamaio.hrms.demo.dataAccess.abstracts.linkDaos;

import kodlamaio.hrms.demo.entities.concretes.links.JobSeekerAdvertisementLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerAdvertisementLinkDao extends JpaRepository<JobSeekerAdvertisementLink, Integer> {
}
