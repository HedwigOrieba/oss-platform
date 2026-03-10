package com.bazotech.store.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
@ToString()
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

    @ManyToOne()
    @JoinColumn(name ="id", nullable = false)
    private StoreItem storeItem;


    @OneToOne(mappedBy = "stagedItem")
    private Product product;

    // TODO: have the UUID be generated according to the merchant standards
    @Column(nullable = false, unique = true)
    @NotNull
    private UUID stagedItemCode;

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

    @Column(name="updated_on")
    private LocalDateTime updatedOn;

    @PostPersist
    protected void onUpdate() {
        this.updatedOn = LocalDateTime.now();
    }
}
