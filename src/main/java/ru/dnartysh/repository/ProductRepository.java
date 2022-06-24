package ru.dnartysh.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dnartysh.model.Product;

@Repository
@Qualifier("product")
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
