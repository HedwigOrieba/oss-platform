package com.bazotech.store.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// TODO: Add maximum reorder level.
@Builder
@Setter
@Getter
@ToString(exclude= {"tags","stagedItems"})
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Entity
@Table(name="store_items")
@AllArgsConstructor
@NoArgsConstructor
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

    // TODO : Open to investigation
    @OneToMany(mappedBy = "storeItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
    @Builder.Default
    private List<StagedItem> stagedItems = new ArrayList<>();

    public void addStagedItem(StagedItem stagedItem) {
        if(!stagedItems.contains(stagedItem)){
            stagedItems.add(stagedItem);
        }
    }

    public void removeStagedItem(StagedItem stagedItem) {
        stagedItems.remove(stagedItem);
    }

    /* Linkage to tags*/
    @ManyToMany
    @Builder.Default
    @JoinTable( name="item_tag_map",
            joinColumns=@JoinColumn(name="id"), inverseJoinColumns=@JoinColumn(name="tag_id") )
    private List<ItemTag> tags = new ArrayList<>();

    /* helper methods for the tags collection */
    public void addTag(ItemTag tag) {
        if (!tags.contains(tag)){
            tags.add(tag);
            tag.getItems().add(this);
        }
    }

    public void removeTag(ItemTag tag) {
        tags.remove(tag);
        tag.getItems().remove(this);
    }





}

