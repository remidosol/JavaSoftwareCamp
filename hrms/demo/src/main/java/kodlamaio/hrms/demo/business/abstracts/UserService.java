package kodlamaio.hrms.demo.business.abstracts;

import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.core.utilities.results.DataResult;

import java.util.List;
import java.util.Optional;

public interface UserService {

    DataResult<List<User>> fetch();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
