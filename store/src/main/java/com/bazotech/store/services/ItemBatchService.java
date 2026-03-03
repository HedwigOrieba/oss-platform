package com.bazotech.store.services;

import com.bazotech.store.domain.InventoryItem;
import com.bazotech.store.domain.ItemBatch;
import com.bazotech.store.repositories.ItemBatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemBatchService {
    private final ItemBatchRepository itemBatchRepository;
    private final InventoryItemService inventoryItemService;

    // create inventory item batch
    public void createItemBatch(){
        var batch = ItemBatch.builder()
                .batchNumber("B1")
                .batchQuantity(10) // less than or equal to the inventory_item Quantity.
                .inventoryItem(inventoryItemService.getInventoryItemById(1L))
                .build();

        itemBatchRepository.save(batch);
        System.out.println("Batch successfully created!!");
    }

    // Get item batch by id
    public ItemBatch getItemBatchById(Long id){
        return itemBatchRepository.findById(id).orElseThrow();
    }
}
