package com.bazotech.store.services;

import com.bazotech.store.domain.InventoryItem;
import com.bazotech.store.repositories.InventoryItemRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryItemService {
    private final InventoryItemRepository inventoryItemRepository;
    private final VendorService vendorService;
    private final MerchantService merchantService;
    private final ItemCategoryService itemCategoryService;

    @Transactional
    public void createInventoryItem(){
        var inventoryItem = InventoryItem.builder()
                .itemName("HP Laptop")
                .itemDescription("A PC for field work")
                .itemUom(InventoryItem.UomType.UNIT)
                .category(itemCategoryService.getItemCategoryById(1L))
                .itemQuantity(40)
                .vendor(vendorService.getVendorById(1L))
                .merchant(merchantService.getMerchantById(1L))
                .build();

        inventoryItemRepository.save(inventoryItem);
        System.out.println("Inventory item created successfully!!");
    }

    // Get an inventory item by id.
    public InventoryItem getInventoryItemById(Long id){
        return inventoryItemRepository.findById(id).orElseThrow();
    }

}
