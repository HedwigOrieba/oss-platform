package com.bazotech.store.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bazotech.store.domain.Store;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {
    List<Store> findByStoreType(Store.StoreType storeType);

    List<Store> findByLocation(String storeLocation);

    Optional<Store> findByStoreName(String name);

    List<Store> findByCreatedOnAfter(LocalDate date);
}
