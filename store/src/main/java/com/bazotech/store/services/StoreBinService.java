package com.bazotech.store.services;

import org.springframework.stereotype.Service;

import com.bazotech.store.domain.StoreBin;
import com.bazotech.store.domain.StoreBin.SlotStatus;
import com.bazotech.store.repositories.StoreBinRepository;
import com.bazotech.store.repositories.StoreShelfRepository;

import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
public class StoreBinService {
	
	private final StoreShelfRepository storeShelfRepository;
	private final StoreBinRepository storeBinRepository;
	
	public void createStoreBin(String binLabel) {
		
		// obtaining a store-shelf
		var shelf = storeShelfRepository.findById(1L).orElseThrow();
		
		// creating a store-bin
		var store_bin = StoreBin.builder()
								.shelf(shelf)
								.slotStaus(SlotStatus.UNRESERVED)
								.binLabel(binLabel)
								.build();
		// save bin
		storeBinRepository.save(store_bin);
		
		System.out.println("Bin created successfully!!");
	}
	
	// get store bin by id.
	public StoreBin getStoreBinById(Long id) {
		return storeBinRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("StoreBin Not Found!"));
	}

	// return list of bins by slot status
	public List<StoreBin> getAllStoreBinsStatus(SlotStatus slotStatus) {
		return storeBinRepository.findAllBySlotStatus(slotStatus);
	}

	// 	update bin
	public void updateStoreBinById(Long id, StoreBin storeBin) {
		boolean changed = false;

		var store_bin = getStoreBinById(id);

		store_bin.setShelf(storeBin.getShelf());
		store_bin.setBinLabel(storeBin.getBinLabel());
		store_bin.setSlotStaus(storeBin.getSlotStaus());

		changed = true;

		if (!changed) {
			throw new RuntimeException("No data changes found");
		}

		storeBinRepository.save(store_bin);
		System.out.println("Bin updated successfully!!");

	}

	// delete bin
	public void deleteStoreBinById(Long id) {
		storeBinRepository.deleteById(id);
		System.out.println("Bin deleted successfully!!");
	}

	// TODO: Batch creation
}
