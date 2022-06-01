package ru.dnartysh.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dnartysh.model.Position;

public interface PositionRepository extends CrudRepository<Position, Integer> {
}
