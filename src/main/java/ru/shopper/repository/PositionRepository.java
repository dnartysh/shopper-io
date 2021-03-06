package ru.shopper.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shopper.model.Position;

@Repository
@Qualifier("position")
public interface PositionRepository extends CrudRepository<Position, Integer> {
    Position findByName(String name);
}
