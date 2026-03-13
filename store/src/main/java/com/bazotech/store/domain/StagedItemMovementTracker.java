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
@ToString(exclude= {"stagedItemStatuses"})
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

    // Item instance to be tracked
    @OneToOne
    @JoinColumn(name = "staging_id")
    @NotNull
    private StagedItem stagedItem;

    @OneToMany(mappedBy = "activeMovement", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<StagedItemStatusTracker> stagedItemStatuses = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type")
    @NotNull
    private MovementType movementType;

    // Supplementary user provided remark during staged item movement
    @Column(name="remark")
    private String remark;

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

    /* Helper methods for staged item status tracker */
    public void addStagedItemStatusTracker(StagedItemStatusTracker stagedItemStatusTracker) {
        if(!stagedItemStatuses.contains(stagedItemStatusTracker)){
            stagedItemStatuses.add(stagedItemStatusTracker);
        }
    }

    public void removeStagedItemStatusTracker(StagedItemStatusTracker stagedItemStatusTracker) {
        stagedItemStatuses.remove(stagedItemStatusTracker);
    }


}
