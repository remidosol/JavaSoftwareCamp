package kodlamaio.homework6.business.abstracts;

import kodlamaio.homework6.entities.concretes.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> fetch();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
