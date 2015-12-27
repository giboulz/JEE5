package com.gbz.yaps.petstore.entity.order;

import java.io.Serializable;
import javax.persistence.*;

import com.gbz.yaps.petstore.entity.catalog.Item;
import com.gbz.yaps.petstore.exception.ValidationException;

/**
 * Entity implementation class for Entity: OrderLine
 *
 */
@Entity
@Table(name = "t_order_line")
public class OrderLine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1726203376340650688L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private Integer quantity;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_fk", nullable = false)
	private Item item;

    public OrderLine() {
    }
	
    public OrderLine(Integer quantity, Item item) {
        this.quantity = quantity;
        this.item = item;
    }

	@PrePersist
	@PreUpdate
	private void validateData() {
		if (quantity == null || quantity < 0)
			throw new ValidationException("Invalid quantity");
//			System.out.println("Invalid quantity");
	}

	public Float getSubTotal() {
		return item.getUnitCost() * quantity;
	}
	// constructeurs, accesseurs
	// méthodes hashcode, equals et toString

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
