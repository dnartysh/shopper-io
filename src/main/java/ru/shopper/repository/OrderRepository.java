package ru.shopper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shopper.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
