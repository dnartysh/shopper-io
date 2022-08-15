package ru.shopper.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shopper.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
