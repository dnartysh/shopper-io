package ru.shopper.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.shopper.model.Location;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {
    List<Location> findAllByUserId(int userId);

    @Query(value = "select id from locations where user_id = :userId order by datetime desc limit 1 offset 1", nativeQuery = true)
    Location findLastLocationByUserId(@Param("userId") int userId);
}
