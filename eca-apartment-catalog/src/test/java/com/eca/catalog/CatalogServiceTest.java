package com.eca.catalog;

import com.eca.catalog.exception.EcaCustomeServiceException;
import com.eca.catalog.repository.AddressRepository;
import com.eca.catalog.repository.CatalogRepository;
import com.eca.catalog.service.impl.CatalogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CatalogServiceTest {

    @InjectMocks
    CatalogService catalogService;

    @Mock
    CatalogRepository catalogRepository;

    @Mock
    AddressRepository addressRepository;

    ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        this.modelMapper = new ModelMapper();
    }
    @Test
    public void test_getApartments_valid_exception() {
        when(catalogRepository.findAll()).thenThrow(new RuntimeException("Exception"));
        Exception ex = Assertions.assertThrows(EcaCustomeServiceException.class, ()-> catalogService.getApartments());
        Assertions.assertTrue((ex).getMessage().contains("Exception"));
    }
    @Test
    public void test_getApartmentById_valid_exception() {
        when(catalogRepository.findByApartmentId(1l)).thenThrow(new RuntimeException("Exception"));
        Exception ex = Assertions.assertThrows(EcaCustomeServiceException.class, ()-> catalogService.getApartmentById(1));
        Assertions.assertTrue((ex).getMessage().contains("Exception"));
    }
}
