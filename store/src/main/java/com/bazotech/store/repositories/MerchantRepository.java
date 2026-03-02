package com.bazotech.store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bazotech.store.domain.Merchant;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant, Long> {
	
	@Query("select m from Merchant m where m.merchantName = :nameOfMerchant")
	Optional<Merchant> fetchMerchantByName(@Param("nameOfMerchant") String merchantName);
	
	@Query("select m from Merchant m where m.merchantEmail = :emailOfMerchant")
	Optional<Merchant> fetchMerchantByEmail(@Param("emailOfMerchant") String merchantEmail);
}
