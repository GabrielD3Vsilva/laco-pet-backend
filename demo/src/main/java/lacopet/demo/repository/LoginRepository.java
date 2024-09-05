package lacopet.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import lacopet.demo.model.User;

public interface LoginRepository extends MongoRepository<User, String> {
    User findByName(String name);
}
