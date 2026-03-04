package com.bazotech.store.services;

//import com.bazotech.store.domain.InventoryItem;
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
    // initialize expiry date
    public void createItemBatch(String batchNumber, int batchQuantity, Long inventoryItemId){
        var batch = ItemBatch.builder()
                .batchNumber(batchNumber)
                .batchQuantity(batchQuantity) // less than or equal to the inventory_item Quantity.
                .inventoryItem(inventoryItemService.getInventoryItemById(inventoryItemId))
                .build();

        itemBatchRepository.save(batch);
        System.out.println("Batch successfully created!!");
    }

    // Get item batch by id
    public ItemBatch getItemBatchById(Long id){
        var batch = itemBatchRepository.findById(id).orElseThrow();
        System.out.println("Batch retrieved: " + batch.toString());
        return batch;
    }
}
