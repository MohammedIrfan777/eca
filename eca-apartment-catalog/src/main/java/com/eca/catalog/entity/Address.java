package com.eca.catalog.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Long addressId;

	@Column(name = "street_name")
	private String streetName;
	
	@Column(name = "zip_code")
	private String zipCode;
	
	@Column(name = "city_name")
	private String cityName;
	
	@Column(name = "state_name")
	private String stateName;
	
	@Column(name = "country_name")
	private String countryName;
	
	@Column(name = "contact_details")
	private String contactDetails;
	
	@JsonBackReference
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "APARTMENT_ID")
    private Apartments apartments;

}
