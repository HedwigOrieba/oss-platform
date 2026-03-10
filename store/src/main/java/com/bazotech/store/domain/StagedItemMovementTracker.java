package com.bazotech.store.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@ToString(exclude= {})
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Entity
@Table(name="staged_item_movement_tracker")
@AllArgsConstructor
@NoArgsConstructor
public class StagedItemMovementTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "tracker_id")
    private Long tracker_id;

//    @ManyToOne()
//    @JoinColumn(name = "id")
//    private StoreItem storeItem;

    // Item instance to be tracked
    @OneToOne
    @JoinColumn(name = "staging_id")
    private StagedItem stagedItem;

    @OneToMany(mappedBy = "activeMovement", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<StagedItemStatusTracker> stagedItemStatuses = new ArrayList<>();

//    @NotNull
//    @Min(value=0, message="Quantity must be non-negative")
//    private Integer quantity = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_status")
    @NotNull
   private MovementType movementType;

    @NotNull
    @Column(name="remark")
    private String remark;   // Remarks for moving an item instance

    @Column(name="created_on")
    private LocalDateTime createdOn;

    @PrePersist
    protected void onCreate() {
        this.createdOn = LocalDateTime.now();
    }


    /* Movement type Enumeration */
    @Getter
    public enum MovementType {

        PROMOTE_TO_PRODUCT("PTP"),
        PROMOTE_TO_STAGING("PTS"),
        DEMOTE_TO_STAGING("DS"),
        DEMOTE_TO_STORE_ITEM("DTS");

        private final String movementLabel;

        MovementType(String movement_label) {
            this.movementLabel = movement_label;
        }
    }
}
