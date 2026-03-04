package com.bazotech.store.services;

import org.springframework.stereotype.Service;

import com.bazotech.store.domain.StoreBin;
import com.bazotech.store.domain.StoreBin.SlotStatus;
import com.bazotech.store.repositories.StoreBinRepository;
import com.bazotech.store.repositories.StoreShelfRepository;

import lombok.AllArgsConstructor;

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
		return storeBinRepository.findById(id).orElseThrow();
	}
}
