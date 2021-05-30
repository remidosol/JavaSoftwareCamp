package kodlamaio.hrms.demo.business.abstracts;

import kodlamaio.hrms.demo.entities.concretes.User;
import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import kodlamaio.hrms.demo.core.utilities.results.Result;

import java.util.List;
import java.util.Optional;

public interface UserService {

    DataResult<List<User>> fetch();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
