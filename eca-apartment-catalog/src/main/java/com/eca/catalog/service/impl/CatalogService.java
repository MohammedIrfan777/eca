package com.eca.catalog.service.impl;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import com.eca.catalog.entity.Address;
import com.eca.catalog.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eca.catalog.dto.ApartmentDto;
import com.eca.catalog.entity.Apartments;
import com.eca.catalog.exception.EcaCustomeServiceException;
import com.eca.catalog.repository.CatalogRepository;
import com.eca.catalog.service.ICatalogService;
import com.google.common.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CatalogService implements ICatalogService {

	@Autowired
	private CatalogRepository catalogRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	ModelMapper modelMapper;

	@SuppressWarnings("serial")
	@Override
	public List<ApartmentDto> getApartments() {
		Type listType = new TypeToken<List<ApartmentDto>>() {}.getType();
		try {
			log.info("inside the CatalogService::getApartments");
			return modelMapper.map(catalogRepository.findAll(), listType);
		} catch (Exception e) {
			log.error("error inside the CatalogService::getApartments", e);
			throw new EcaCustomeServiceException(e);
		}
	}

	@Override
	public int persistApartments(ApartmentDto apartments) {
		try {
			log.info("inside the CatalogService::persistApartments");
			Apartments pApartments = modelMapper.map(apartments, Apartments.class);
			pApartments.setRegisterDate(new Date());
			Apartments perApart = catalogRepository.save(pApartments);
			Address address = modelMapper.map(apartments.getAddress(), Address.class);
			address.setApartments(perApart);
			addressRepository.save(address);
			log.info("insert success::persistApartments");
			return 1;
		} catch (Exception e) {
			log.error("error inside the CatalogService::persistApartments", e);
			throw new EcaCustomeServiceException(e);
		}
	}

	@Override
	public ApartmentDto getApartmentById(long id) {
		try {
			log.info("inside the CatalogService::getApartmentById", id);
			Apartments apartments = catalogRepository.findByApartmentId(id);
			return modelMapper.map(apartments, ApartmentDto.class);
		} catch (Exception e) {
			log.error("error inside the CatalogService::getApartmentById", e);
			throw new EcaCustomeServiceException(e);
		}
	}
}
