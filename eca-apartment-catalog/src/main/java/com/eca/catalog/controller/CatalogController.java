package com.eca.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eca.catalog.dto.ApartmentDto;
import com.eca.catalog.service.impl.CatalogService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
public class CatalogController {
	
	
	@Autowired
	private CatalogService catalogService;

	@CrossOrigin
	@GetMapping(value = "/v1/catalog/list")
	public List<ApartmentDto> getApartments() {
		log.info("get list  of departments api::getApartments");
		return catalogService.getApartments();
	}
	
	
	@CrossOrigin
	@PostMapping(value = "/v1/catalog/register")
	public String persistApartments(@RequestBody ApartmentDto apartments) {
		log.info("register new apartment::persistApartments");
		catalogService.persistApartments(apartments);
		return "succesfully register !!!";
	}
	
	@CrossOrigin
	@GetMapping(value = "/v1/catalog/getapartment")
	public ApartmentDto getApartmentById(@RequestParam(required = true) Long apartmentId) {
		log.info("get the apartment details by id::getApartmentById");
		return catalogService.getApartmentById(apartmentId);
	}

}
