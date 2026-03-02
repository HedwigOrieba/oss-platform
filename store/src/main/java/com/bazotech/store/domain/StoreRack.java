package com.bazotech.store.domain;

import java.util.ArrayList;
import java.util.List;

import com.bazotech.store.domain.StoreBin.SlotStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Table(name="store_racks")
@Entity
public class StoreRack {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name="rack_id")
	private Long rackId;
	
	/* Store Relationship */
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;
	
	
	@Column(name="rack_label")
	private String rackLabel;
	
	/* Shelf Relationship */
	@Builder.Default
	@OneToMany(mappedBy="rack", cascade=CascadeType.ALL, orphanRemoval=true)
	List<StoreShelf> rackshelves = new ArrayList<>();
	
	/** Helper method to add a store rack **/
	public void addRackShelf(StoreShelf storeshelf) {
		if(!rackshelves.contains(storeshelf)) {
			rackshelves.add(storeshelf);
			storeshelf.setRack(this);
		}
	}
	
	/** Helper method to remove a store rack **/
	public void removeRackShelf(StoreShelf storeshelf) {
		rackshelves.remove(storeshelf);
		storeshelf.setRack(null);
	}
	
	
}
