package com.bazotech.store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bazotech.store.domain.Vendor;

@Repository
public interface VendorRepository extends CrudRepository<Vendor, Long>{
	
	@Query("select v from Vendor v where v.vendorName = :nameOfVendor")
	Optional<Vendor> fetchVendorByName(@Param("nameOfVendor") String vendorName);
	
	@Query("select v from Vendor v where v.vendorEmail = :emailOfVendor")
	Optional<Vendor> fetchVendorByEmail(@Param("emailOfVendor") String vendorEmail);

}
