package ru.dnartysh.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dnartysh.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
