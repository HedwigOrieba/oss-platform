package com.bazotech.store.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bazotech.store.domain.StoreRack;

@Repository
public interface StoreRackRepository extends CrudRepository<StoreRack, Long> {}
