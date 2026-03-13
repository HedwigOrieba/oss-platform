package com.bazotech.store.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Entity
@Table(name="staged_item_status_tracker")
@AllArgsConstructor
@NoArgsConstructor
public class StagedItemStatusTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "status_tracker_id")
    private Long statusTrackerId;


    // Item instance to be tracked
    @OneToOne
    @JoinColumn(name = "staging_id")
    @NotNull
    private StagedItem stagedItem;

    @OneToOne
    @JoinColumn(name = "tracker_id")
    @NotNull
    private StagedItemMovementTracker activeMovement;

    @Enumerated(EnumType.STRING)
    @Column(name = "staged_item_status")
    @NotNull
    private StagedItemStatus stagedItemStatus;

    // Supplementary user provided remark observed when staged item changes status
    @Column(name="remark")
    private String remark;

    @Column(name="created_on")
    private LocalDateTime createdOn;

    @PrePersist
    protected void onCreate() {
        this.createdOn = LocalDateTime.now();
    }


    /* Status type Enumeration */
    public enum StagedItemStatus {
        SOLD,
        EXPIRED ,
        REJECTED,
        DAMAGED,
        REPAIRED,
        RETURNED,
        BOOKED,
        PROCESSED,
        PENDING,
        PROMOTED,
        DEMOTED
    }
    }



