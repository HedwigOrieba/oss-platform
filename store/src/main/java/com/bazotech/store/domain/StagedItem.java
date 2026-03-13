package com.bazotech.store.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
@ToString(exclude = {"movementTrackers","statusTrackers"})
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Entity
@Table(name="item_staging_area")
@AllArgsConstructor
@NoArgsConstructor
public class StagedItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="staging_id")
    @EqualsAndHashCode.Include
    private Long stagingId;

    // Linkage Point to Store Item
    @ManyToOne
    @JoinColumn(name ="id", nullable = false)
    private StoreItem storeItem;

    // Linkage Point to Product
    @OneToOne(mappedBy = "stagedItem")
    private Product product;

    @Column(name = "product_image")
    @NotEmpty(message = "Product image must not be empty ")
    private String productImage;

    // Mandatory system-defined id for staged item for internal tracking
    @Column(name = "system_item_code", nullable = false)
    @NotNull
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID systemItemCode;

    // Optional user-defined id for staged item
    @Column(name = "custom_identifier", nullable = true)
    private String customIdentifier;

    @OneToMany(mappedBy = "stagedItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<StagedItemMovementTracker>  movementTrackers = new ArrayList<>();


    @OneToMany(mappedBy = "stagedItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<StagedItemStatusTracker>  statusTrackers = new ArrayList<>();

    @Column(name="staged_on")
    private LocalDateTime stagedOn;

    @PrePersist
    protected void onCreate() {
        this.stagedOn = LocalDateTime.now();
    }

//    @Column(name="updated_on")
//    private LocalDateTime updatedOn;
//
//    @PostPersist
//    protected void onUpdate() {
//        this.updatedOn = LocalDateTime.now();
//    }

    /* Helper methods for staged item movement tracker */
    public void addMovementTracker(StagedItemMovementTracker movementTracker) {
        if(!movementTrackers.contains(movementTracker)) {
            movementTrackers.add(movementTracker);
        }
    }

    public void removeMovementTracker(StagedItemMovementTracker movementTracker) {
        movementTrackers.remove(movementTracker);
    }

    /* Helper methods for staged item status tracker */
    public void addStatusTracker(StagedItemStatusTracker statusTracker) {
        if(!statusTrackers.contains(statusTracker)) {
            statusTrackers.add(statusTracker);
        }
    }
    public void removeStatusTracker(StagedItemStatusTracker statusTracker) {
        statusTrackers.remove(statusTracker);
    }
}
