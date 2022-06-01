package ru.dnartysh.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dnartysh.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
