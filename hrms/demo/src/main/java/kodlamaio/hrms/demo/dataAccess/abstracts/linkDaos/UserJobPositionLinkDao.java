package kodlamaio.hrms.demo.dataAccess.abstracts.linkDaos;

import kodlamaio.hrms.demo.entities.concretes.links.UserJobPositionLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJobPositionLinkDao extends JpaRepository<UserJobPositionLink, Integer> {
}
