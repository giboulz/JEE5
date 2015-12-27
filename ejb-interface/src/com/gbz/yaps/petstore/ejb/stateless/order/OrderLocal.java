package com.gbz.yaps.petstore.ejb.stateless.order;

import java.util.List;

import javax.ejb.Local;

import com.gbz.yaps.petstore.dto.CartItem;
import com.gbz.yaps.petstore.entity.Address;
import com.gbz.yaps.petstore.entity.customer.Customer;
import com.gbz.yaps.petstore.entity.order.CreditCard;
import com.gbz.yaps.petstore.entity.order.Order;

@Local
public interface OrderLocal {
	Order createOrder(Customer customer, Address deliveryAddress, CreditCard creditCard, List<CartItem> cartItems);
}
