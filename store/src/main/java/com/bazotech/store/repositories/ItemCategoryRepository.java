package com.bazotech.store.repositories;

import com.bazotech.store.domain.ItemCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends CrudRepository<ItemCategory, Long> {
    boolean existsByCategoryName(String categoryName);
}
