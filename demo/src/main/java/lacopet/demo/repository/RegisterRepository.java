package lacopet.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import lacopet.demo.model.User;

public interface RegisterRepository extends MongoRepository<User, String> {
    
}
