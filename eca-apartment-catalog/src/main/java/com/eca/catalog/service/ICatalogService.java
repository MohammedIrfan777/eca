package com.eca.catalog.service;

import java.util.List;

import com.eca.catalog.dto.ApartmentDto;

public interface ICatalogService {

    public List<ApartmentDto> getApartments();
    
    public int persistApartments(ApartmentDto apartments);
    
    public ApartmentDto getApartmentById(long id);

    
}
