package com.eca.catalog.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentDto {

	private Long apartmentId;
	
	@NotBlank
	private String name;
	
	private String superVisorName;
	
	private int totalFloor;
	
	private String superVisorContact;
	
	@NotNull
	private AddressDto address;

}
