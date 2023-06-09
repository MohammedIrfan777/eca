package com.eca.catalog.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

	private Long addressId;
	
	@NotBlank
	private String streetName;
	
	@NotBlank
	private String zipCode;
	
	@NotBlank
	private String cityName;
	
	@NotBlank
	private String stateName;
	
	@NotBlank
	private String countryName;
	
	private String contactDetails;

}
