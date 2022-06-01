package ru.dnartysh.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dnartysh.model.Storage;

public interface StorageRepository extends CrudRepository<Storage, Integer> {
}
