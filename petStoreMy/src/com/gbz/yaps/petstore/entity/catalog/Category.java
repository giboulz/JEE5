package com.gbz.yaps.petstore.entity.catalog;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.gbz.yaps.petstore.exception.ValidationException;





/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity
@Table(name = "t_category")
public class Category implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2571582006248484223L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 30)
	private String name;
	@Column(nullable = false)
	private String description;
	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@OrderBy("name ASC")
	private List<Product> products;

	@PrePersist
	@PreUpdate
	private void validateData() {

		if (name == null || "".equals(name))
			throw new ValidationException("Invalid name");
//			System.out.println("Invalid Name");
		if (description == null || "".equals(description))
			throw new ValidationException("Invalid description");
//			System.out.println("Invalid Description");
	}
	// constructeurs, accesseurs
	// méthodes hashcode, equals et toString

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
