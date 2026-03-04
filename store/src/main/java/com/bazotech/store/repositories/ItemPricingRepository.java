package com.bazotech.store.repositories;

import com.bazotech.store.domain.ItemPricing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPricingRepository extends CrudRepository<ItemPricing, Long> {
	//TODO: use query notation
	Iterable<ItemPricing> findByItem_ItemId(Long inventoryItemId);
}
