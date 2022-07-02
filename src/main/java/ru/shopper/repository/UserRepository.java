package ru.shopper.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shopper.model.User;

@Repository
@Qualifier("user")
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
