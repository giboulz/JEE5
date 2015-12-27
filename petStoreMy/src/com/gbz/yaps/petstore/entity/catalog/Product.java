package com.gbz.yaps.petstore.entity.catalog;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.gbz.yaps.petstore.exception.ValidationException;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity
@Table(name = "t_product")
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7191034701785269595L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 30)
	private String name;
	@Column(nullable = false)
	private String description;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_fk", nullable = false)
	private Category category;
	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@OrderBy("name ASC")
	private List<Item> items;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
