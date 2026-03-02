package com.bazotech.store.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bazotech.store.domain.VendorMerchantAssociation;

@Repository
public interface VendorMerchantRepository extends CrudRepository<VendorMerchantAssociation, Long> {

}
