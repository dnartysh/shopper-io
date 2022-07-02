package ru.shopper.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shopper.model.Shop;

@Repository
@Qualifier("shop")
public interface ShopRepository extends CrudRepository<Shop, Integer> {
}
