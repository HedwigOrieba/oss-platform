package com.bazotech.store.services;

import com.bazotech.store.domain.ItemPricing;
import com.bazotech.store.repositories.ItemPricingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ItemPricingService {

    private final ItemPricingRepository itemPricingRepository;
    private final InventoryItemService inventoryItemService;

    public void createItemPrice(String priceCategory){

        var itemPrice = ItemPricing.builder()
                                .item(inventoryItemService.getInventoryItemById(1L))
                                .priceCategory(getPriceCategory(priceCategory))
                                .categoryAmount(setCategoryAmount(priceCategory))
                                .build();
        itemPricingRepository.save(itemPrice);
        System.out.println(priceCategory + " pricing created successfully!!");
    }


    // get pricing level by price category
    private ItemPricing.PriceCategory getPriceCategory(String priceCategory){
        switch(priceCategory){
            case "gold":
                return ItemPricing.PriceCategory.GOLD;
            case "platinum":
                return ItemPricing.PriceCategory.PLATINUM;

            case "silver":
                return ItemPricing.PriceCategory.SILVER;
            default:
                return ItemPricing.PriceCategory.GOLD;
        }
    }

    // get amount by price category
    private BigDecimal setCategoryAmount(String priceCategory){
        switch(priceCategory){
            case "gold":
                return BigDecimal.valueOf(1000.00);
            case "platinum":
                return BigDecimal.valueOf(2000.00);

            case "silver":
                return BigDecimal.valueOf(3000.00);
            default:
                return BigDecimal.valueOf(1000.00);
        }
    }

    // method to get the list of prices associated to an item


}
