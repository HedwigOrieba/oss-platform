package com.bazotech.store.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString(exclude= {})
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Entity
@Table(name="store_items")
public class StoreItem {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private StoreItemId id;

    @ManyToOne
    @MapsId("storeId")
    @JoinColumn(name="store_id")
    private Store store;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name="item_id")
    private InventoryItem item;

    @ManyToOne(optional = false) 
    @MapsId("batchId") 
    @JoinColumn(name = "batch_id", nullable = false) 
    private ItemBatch itemBatch;
    
    @ManyToOne
    @JoinColumn(name="price_id")
    private ItemPricing price;
    
    @Builder.Default
    @Column(name="items_in_stock", nullable=false)
    private Integer itemsInStock = 0;

    @Column(name="item_reorder_level", nullable=false)
    private Integer itemReorderLevel;

    @Builder.Default
    @Column(name="item_posting_on", nullable=false, updatable=false)
    private LocalDateTime itemPostingOn = LocalDateTime.now();
    
    @OneToOne
    @JoinColumn(name="bin_id", nullable=false, unique=true) 
    private StoreBin bin;
    
}

