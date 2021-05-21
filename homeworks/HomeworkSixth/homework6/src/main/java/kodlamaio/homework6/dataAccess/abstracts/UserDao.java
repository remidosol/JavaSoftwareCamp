package kodlamaio.homework6.dataAccess.abstracts;


import kodlamaio.homework6.entities.concretes.JobSeeker;
import kodlamaio.homework6.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    @Query("SELECT t FROM User t WHERE t.email = ?1")
    Optional<User> findByEmail(String email);

    @Query("SELECT t FROM User t WHERE t.id = ?1")
    Optional<User> findById(Long id);

}
