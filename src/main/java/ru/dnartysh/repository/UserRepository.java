package ru.dnartysh.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dnartysh.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
