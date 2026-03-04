package com.bazotech.store.repositories;

import com.bazotech.store.domain.StoreItem;
import com.bazotech.store.domain.StoreItemId;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreItemRepository extends CrudRepository<StoreItem, StoreItemId> {
}
