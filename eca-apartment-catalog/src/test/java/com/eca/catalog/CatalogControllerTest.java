package com.eca.catalog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.eca.catalog.controller.CatalogController;
import com.eca.catalog.dto.AddressDto;
import com.eca.catalog.dto.ApartmentDto;
import com.eca.catalog.service.impl.CatalogService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CatalogControllerTest {

	@InjectMocks
	private CatalogController catalogController;

	@Mock
	private CatalogService catalogService;

	@Test
	public void test_getApartments_valid() {
		when(catalogService.getApartments()).thenReturn(getApartmentDetails());
		List<ApartmentDto> apartmentDtos = catalogController.getApartments();
		assertNotNull(apartmentDtos);

	}

	@Test
	public void test_persistApartments_valid() {
		ApartmentDto apartmentDto = getApartmentDto();
		when(catalogService.persistApartments(apartmentDto)).thenReturn(1);
		String response = catalogController.persistApartments(apartmentDto);
		assertEquals("succesfully register !!!", response);
	}

	@Test
	public void test_getApartmentById_valid() {
		ApartmentDto apartmentDto = getApartmentDto();
		when(catalogService.getApartmentById(1)).thenReturn(getApartmentDto());
		ApartmentDto response = catalogController.getApartmentById(1l);
		assertNotNull(response);
	}

	private List<ApartmentDto> getApartmentDetails() {
		List<ApartmentDto> apartmentDtos = new ArrayList<ApartmentDto>();
		ApartmentDto apartmentDto = getApartmentDto();
		apartmentDtos.add(apartmentDto);
		return apartmentDtos;
	}

	private ApartmentDto getApartmentDto() {
		ApartmentDto apartmentDto = new ApartmentDto();
		apartmentDto.setName("test apartment");
		apartmentDto.setSuperVisorContact("876542345");
		apartmentDto.setSuperVisorName("ann");
		apartmentDto.setTotalFloor(5);
		AddressDto addressDto = new AddressDto();
		addressDto.setCityName("test");
		addressDto.setCountryName("india");
		addressDto.setStreetName("rj nagar");
		apartmentDto.setAddress(addressDto);
		return apartmentDto;
	}

}
