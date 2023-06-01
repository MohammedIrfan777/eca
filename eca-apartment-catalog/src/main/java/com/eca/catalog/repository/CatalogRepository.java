package com.eca.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eca.catalog.entity.Apartments;

@Repository
public interface CatalogRepository extends JpaRepository<Apartments, Long> {
	
	Apartments findByApartmentId(Long id);

}
