package com.gbz.yaps.petstore.entity.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.gbz.yaps.petstore.entity.Address;
import com.gbz.yaps.petstore.entity.customer.Customer;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
@Table(name = "t_order")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5929251703940687232L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "order_date", updatable = false)
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_fk", nullable = false)
	private Customer customer;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_fk", nullable = false)
	private Address deliveryAddress;
	@Embedded
	private CreditCard creditCard = new CreditCard();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "t_order_order_line", joinColumns = { @JoinColumn(name = "order_fk") }, inverseJoinColumns = {
			@JoinColumn(name = "order_line_fk") })
	private List<OrderLine> orderLines;

    public Order() {
    }

    public Order(Customer customer, Address deliveryAddress, CreditCard creditCard) {
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
        this.creditCard = creditCard;
    }

	@PrePersist
	private void setDefaultData() {
		orderDate = new Date();
	}

	public Float getTotal() {
		if (orderLines == null || orderLines.isEmpty())
			return 0f;
		Float total = 0f;
		for (OrderLine orderLine : orderLines) {
			total += (orderLine.getSubTotal());
		}
		return total;
	}
	// constructeurs, accesseurs
	// méthodes hashcode, equals et toString

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
}
