package com.bazotech.store.services;

import com.bazotech.store.domain.ItemCategory;
import com.bazotech.store.repositories.ItemCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ItemCategoryService {

    // reference the repository
    private final ItemCategoryRepository itemCategoryRepository;

    // create an item category
    public void createItemCategory(String categoryName, String categoryDesc){

        if(itemCategoryRepository.existsByCategoryName(categoryName)){
            System.out.println("Item category already exists!!");
            return;
        }

        var item_category = ItemCategory.builder()
                .categoryName(categoryName)
                .categoryDescription(categoryDesc)
                .build();

        itemCategoryRepository.save(item_category);
        System.out.println("Item category successfully saved!!");
    }

    // fetch item category by id.
    public ItemCategory getItemCategoryById(Long id){
        return itemCategoryRepository.findById(id).orElseThrow();
    }

}
