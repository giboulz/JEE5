package com.gbz.petstore.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.xml.ws.WebServiceRef;

import com.gbz.yaps.petstore.dto.CartItem;
import com.gbz.yaps.petstore.ejb.stateless.catalog.CatalogLocal;
import com.gbz.yaps.petstore.ejb.stateless.order.OrderLocal;
import com.gbz.yaps.petstore.entity.Address;
import com.gbz.yaps.petstore.entity.catalog.Item;
import com.gbz.yaps.petstore.entity.customer.Customer;
import com.gbz.yaps.petstore.entity.order.CreditCard;
import com.gbz.yaps.petstore.entity.order.Order;
import com.gbz.yaps.petstore.exception.CreditCardException;
import com.gbz.yaps.petstore.ejb.statefull.shoppingCart.ShoppingCartServiceLocal; 

import java.util.List;

/**
 * @author Antonio Goncalves
 */
@ManagedBean(name = "cart", eager = true)
@SessionScoped
public class ShoppingCartController extends Controller {

	// ======================================
	// = Attributes =
	// ======================================
	@EJB
	private ShoppingCartServiceLocal shoppingCartBean;
	@EJB
	private CatalogLocal catalogBean;
	@EJB
	private OrderLocal orderBean;
	// @WebServiceRef
	
	//@WebServiceRef(wsdlLocation = "http://localhost:8080/barkbank/ValidationService?WSDL")
	
//	private ValidationService validationService;

	private final String cname = this.getClass().getName();

	private CreditCard creditCard = new CreditCard();
	
	// Customer et Address sont initialis�s par le faces-config.xml
	
	@ManagedProperty(value = "#{account.customer}")
	private Customer customer;
	
	@ManagedProperty(value = "#{account.homeAddress}")
	private Address deliveryAddress;
	
	private Order order;

	// ======================================
	// = Business methods =
	// ======================================

	public String addItemToCart() {
		final String mname = "addItemToCart";
		logger.entering(cname, mname);

		String navigateTo = null;

		try {
			Item item = catalogBean.findItem(getParamId("itemId"));
			shoppingCartBean.addItem(item);
			navigateTo = "item.added";
		} catch (Exception e) {
			addMessage(cname, mname, e);
		}

		logger.exiting(cname, mname, navigateTo);
		return navigateTo;
	}

	public String removeItemFromCart() {
		final String mname = "removeItemFromCart";
		logger.entering(cname, mname);

		String navigateTo = null;

		try {
			Item item = catalogBean.findItem(getParamId("itemId"));
			shoppingCartBean.removeItem(item);
		} catch (Exception e) {
			addMessage(cname, mname, e);
		}

		logger.exiting(cname, mname, navigateTo);
		return navigateTo;
	}

	public String updateQuantity() {
		final String mname = "updateQuantity";
		logger.entering(cname, mname);

		String navigateTo = null;

		logger.exiting(cname, mname, navigateTo);
		return navigateTo;
	}

	public String checkout() {
		final String mname = "checkout";
		logger.entering(cname, mname);

		String navigateTo = "cart.checked.out";

		logger.exiting(cname, mname, navigateTo);
		return navigateTo;
	}

	public String confirmOrder() {
		final String mname = "confirmOrder";
		logger.entering(cname, mname);

		String navigateTo = null;

		try {
			// Calls the web service to validate the credit card
//			validateCreditCard(creditCard);
			order = orderBean.createOrder(customer, deliveryAddress, creditCard, shoppingCartBean.getCartItems());
			shoppingCartBean.empty();
			navigateTo = "order.confirmed";
		} catch (Exception e) {
			addMessage(cname, mname, e);
		}

		logger.exiting(cname, mname, navigateTo);
		return navigateTo;
	}

	public Float getTotal() {
		return shoppingCartBean.getTotal();
	}

	public List<CartItem> getCartItems() {
		return shoppingCartBean.getCartItems();
	}

	// ======================================
	// = Getters & Setters =
	// ======================================

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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	// ======================================
	// = Getters & Setters =
	// ======================================

	/*private void validateCreditCard(CreditCard creditCard) {
		String statusCard;

		Validation validation = validationService.getValidationPort();
		statusCard = validation.validateCard(creditCard.getCreditCardNumber(), creditCard.getCreditCardType(),
				creditCard.getCreditCardExpDate());
		logger.info("Validation Service has been called. isCardValid=" + statusCard);

		if (!"OK".equals(statusCard))
			throw new CreditCardException("Credit Card is invalid : " + statusCard);

	}*/
}