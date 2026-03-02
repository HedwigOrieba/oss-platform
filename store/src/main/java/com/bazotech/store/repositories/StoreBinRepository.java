package com.bazotech.store.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bazotech.store.domain.StoreBin;

@Repository
public interface StoreBinRepository extends CrudRepository<StoreBin, Long> {}
