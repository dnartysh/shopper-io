package ru.dnartysh.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dnartysh.model.Storage;

@Repository
@Qualifier("storage")
public interface StorageRepository extends CrudRepository<Storage, Integer> {
}
