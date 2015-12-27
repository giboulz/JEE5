package com.gbz.yaps.petstore.entity.catalog;

import java.io.Serializable;
import javax.persistence.*;

import com.gbz.yaps.petstore.exception.ValidationException;

/**
 * Entity implementation class for Entity: Item
 *
 */
@Entity
@Table(name = "t_item")
public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5366756165257604387L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 30)
	private String name;
	@Column(name = "unit_cost", nullable = false)
	private Float unitCost;
	@Column(name = "image_path")
	private String imagePath;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_fk", nullable = false)
	private Product product;

	@PrePersist
	@PreUpdate
	private void validateData() {
		if (name == null || "".equals(name))
			throw new ValidationException("Invalid name");	
//			System.out.println("Invalid Name");
	}
	// constructeurs, accesseurs
	// méthodes hashcode, equals et toString

	public float getUnitCost() {
		// TODO Auto-generated method stub
		return 11;
	}

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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setUnitCost(Float unitCost) {
		this.unitCost = unitCost;
	}
}
