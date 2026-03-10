package com.bazotech.store.services;

import org.springframework.stereotype.Service;

import com.bazotech.store.domain.StoreShelf;
import com.bazotech.store.repositories.StoreRackRepository;
import com.bazotech.store.repositories.StoreShelfRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StoreShelfService {
	
	private final StoreShelfRepository storeShelfRepository;
	private final StoreRackRepository storeRackRepository;
	
	// create a shelf
	public void createShelf(String shelfLabel) {
		
		// obtain the first rack.
		var rack = storeRackRepository.findById(1L).orElseThrow();
		
		// bind three-shelves to the rack.
		var shelf = StoreShelf.builder().rack(rack).shelfLabel(shelfLabel).build();
		
		// save the shelf.
		storeShelfRepository.save(shelf);
		
		// create a shelf
		System.out.println("Store shelf created successfully!!");
	}

	// find shelf by id
	public StoreShelf getShelfById(Long id) {
		return storeShelfRepository.findById(1L)
				.orElseThrow(()->new RuntimeException("Store shelf not found!"));
	}

	// Update Store Shelf
	public void updateShelfById(Long id,StoreShelf shelf) {
		boolean changed = false;

		StoreShelf storeShelf = getShelfById(id);

		storeShelf.setShelfLabel(shelf.getShelfLabel());
		storeShelf.setRack(shelf.getRack());

		changed = true;

		if (!changed) {
			throw new RuntimeException("No data changes found");
		}

		storeShelfRepository.save(storeShelf);
		System.out.println("Store shelf updated successfully!!");

	}

	// Delete shelf
	public void deleteShelfById(Long id) {
		storeShelfRepository.deleteById(1L);
		System.out.println("Store shelf deleted successfully!!");
	}
}
