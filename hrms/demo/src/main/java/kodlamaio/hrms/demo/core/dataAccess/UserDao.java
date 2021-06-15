package kodlamaio.hrms.demo.core.dataAccess;


import kodlamaio.hrms.demo.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    @Query("SELECT t FROM User t WHERE t.id = ?1")
    Optional<User> findById(Long id);

    @Query("SELECT t FROM User t WHERE t.email = ?1")
    Optional<User> findByEmail(String email);

}
