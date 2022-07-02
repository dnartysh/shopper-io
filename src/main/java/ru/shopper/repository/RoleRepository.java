package ru.shopper.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shopper.model.Role;

@Repository
@Qualifier("role")
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
