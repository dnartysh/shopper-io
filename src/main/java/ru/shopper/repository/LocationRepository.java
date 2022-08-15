package ru.shopper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shopper.model.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {
}
