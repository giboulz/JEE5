package com.gbz.yaps.petstore.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.gbz.yaps.petstore.exception.ValidationException;

/**
 * Entity implementation class for Entity: Address
 *
 */
@Entity
@Table(name = "t_address")
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2029697377127932720L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String street1;
	private String street2;
	@Column(nullable = false, length = 100)
	private String city;
	private String state;
	@Column(name = "zip_code", nullable = false, length = 10)
	private String zipcode;
	@Column(nullable = false, length = 50)
	private String country;

	@PrePersist
	@PreUpdate
	private void validateData() {
		if (street1 == null || "".equals(street1))
			throw new ValidationException("Invalid street");
		// System.out.println("Invalid Street");
		if (city == null || "".equals(city))
			throw new ValidationException("Invalid city");
		// System.out.println("Invalid city");
		if (zipcode == null || "".equals(zipcode))
			throw new ValidationException("Invalid zip code");
		// System.out.println("Invalid zip code");
		if (country == null || "".equals(country))
			throw new ValidationException("Invalid country");
		// System.out.println("Invalid country");
	}
	// constructeurs, accesseurs
	// méthodes hashcode, equals et toString

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
