package com.bazotech.store.services;

import com.bazotech.store.domain.StoreRack;
import com.bazotech.store.exception.StoreNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.bazotech.store.domain.Store;
import com.bazotech.store.repositories.StoreRepository;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class StoreService {
	
	/* store-service repository. */
	private final StoreRepository storeRepository;
	
	/* create a store */ 
	public void createStore() {
		var store = Store.builder()
						.storeName("Main Store")
						.storeLocation("Main Street")
						.storeType(Store.StoreType.MAIN)
						.storeDescription("This is the main store.")
						.build();
		
		storeRepository.save(store);
		log.info("Store created {}", store);
	}

	// get StoreById
	public Store getStoreById(Long storeId) {
		return storeRepository.findById(storeId)
				.orElseThrow(()-> new StoreNotFoundException("Store with id: " + storeId + " not found"));
	}

	// Update Store
	public void updateStoreById(Long id,Store store) {

		boolean changed = false;
		Store oldStore = storeRepository.findById(id)
				.orElseThrow(()-> new StoreNotFoundException("Store with id: " + id + " not found"));

		oldStore.setStoreName(store.getStoreName());
		oldStore.setStoreLocation(store.getStoreLocation());
		oldStore.setStoreType(store.getStoreType());
		oldStore.setStoreDescription(store.getStoreDescription());

		changed = true;
		if (!changed) {
			throw new RuntimeException("No data changes found");
		}

		storeRepository.save(store);
		log.info("Store updated {}", store);
	}

	// delete Store
	public void deleteStoreById(Long id) {
		storeRepository.deleteById(id);
		log.info("Store deleted {}", id);
	}

	// get all stores
	public List<Store> getAllStores() {
		return (List<Store>) storeRepository.findAll();
	}

	// get stores by Type
	public List<Store> getAllStoresByStoreType(Store.StoreType storeType) {
		 return storeRepository.findByStoreType(storeType);
	}

	// get Stores by Location
	public List<Store> getAllStoresByStoreLocation(String storeLocation) {
		return storeRepository.findByLocation(storeLocation);
	}

	// search store by store name
	public Store getStoreByName(String name) {
		return storeRepository.findByStoreName(name)
				.orElseThrow(()-> new StoreNotFoundException("Store with name: " + name + " not found"));
	}

	 // relocate store to new location (update of store location)
	public void relocateStore(Long storeId, String newLocation) {
		boolean changed = false;
		// check if store exists
		Store store = getStoreById(storeId);
		store.setStoreLocation(newLocation);
		changed = true;

		if (!changed) {
			throw new RuntimeException("No data changes found");
		}

		storeRepository.save(store);
		log.info("Store relocated {}", store);

	}

	// return created stores after a certain date
	public List<Store> getStoresCreatedAfter(LocalDate date) {
		return storeRepository.findByCreatedOnAfter(date);
	}

	// Assign rack to Store
	public void assignRackToStore(Long storeId, StoreRack rack) {
		Store store = getStoreById(storeId);
		store.addStoreRack(rack);
		log.info("Rack assigned {} to {}", rack, store);
	}

	// Get Total Rack Capacity
	public int getTotalRacks(Long storeId) {
		Store store = getStoreById(storeId);

		int numberOfRacks = store.getStoreRacks().size();
		log.info("Rack count for store [{}]: {}", storeId, numberOfRacks);

		return numberOfRacks;

	}


}
