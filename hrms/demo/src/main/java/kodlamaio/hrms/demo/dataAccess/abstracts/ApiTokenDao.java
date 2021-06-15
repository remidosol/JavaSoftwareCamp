package kodlamaio.hrms.demo.dataAccess.abstracts;

import kodlamaio.hrms.demo.entities.concretes.ApiToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiTokenDao extends JpaRepository<ApiToken, Integer> {

    List<ApiToken> getByUserId_UserId(Long userId);

    @Query("SELECT t FROM ApiToken t WHERE t.token = ?1")
    ApiToken getByToken(String token);
}
