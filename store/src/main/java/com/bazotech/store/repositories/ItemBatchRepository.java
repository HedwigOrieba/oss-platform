package com.bazotech.store.repositories;

import com.bazotech.store.domain.ItemBatch;
import org.springframework.data.repository.CrudRepository;

public interface ItemBatchRepository extends CrudRepository<ItemBatch,Long> {
}
