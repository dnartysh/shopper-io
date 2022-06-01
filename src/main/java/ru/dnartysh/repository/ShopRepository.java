package ru.dnartysh.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dnartysh.model.Shop;

public interface ShopRepository extends CrudRepository<Shop, Integer> {
}
