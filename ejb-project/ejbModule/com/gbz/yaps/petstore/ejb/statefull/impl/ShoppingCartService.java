package com.gbz.yaps.petstore.ejb.statefull.impl;

import java.util.ArrayList;
import com.gbz.yaps.petstore.ejb.statefull.shoppingCart.ShoppingCartServiceLocal;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Stateful;

import com.gbz.yaps.petstore.dto.CartItem;
import com.gbz.yaps.petstore.entity.catalog.Item;
import com.gbz.yaps.petstore.utils.Constants;

@Stateful(name = "ShoppingCartSB")
@Local(ShoppingCartServiceLocal.class)
public class ShoppingCartService implements ShoppingCartServiceLocal {
	 // ======================================
    // =             Attributes             =
    // ======================================

    private List<CartItem> cartItems;

    private final String cname = this.getClass().getName();
    private Logger logger = Logger.getLogger(Constants.LOGGER_STATEFUL);

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PostConstruct
    public void initialize() {
        String mname = "initialize";
        logger.entering(cname, mname);

        cartItems = new ArrayList<CartItem>();
    }

    @PreDestroy
    public void clear() {
        String mname = "clear";
        logger.entering(cname, mname);

        cartItems = null;
    }

    // ======================================
    // =          Business methods          =
    // ======================================

    public void addItem(Item item) {
        String mname = "addItem";
        logger.entering(cname, mname, item);

        boolean itemFound = false;
        for (CartItem cartItem : cartItems) {
            // If the item already exists in the shopping cart, it updates the quantity
            if (cartItem.getItem().equals(item)) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                itemFound = true;
            }
        }
        if (!itemFound)
            // Otherwise it adds it to the shopping cart
            cartItems.add(new CartItem(item, 1));

        logger.exiting(cname, mname, cartItems.size());
    }

    public void removeItem(Item item) {
        String mname = "removeItem";
        logger.entering(cname, mname, item);

        for (CartItem cartItem : cartItems) {
            if (cartItem.getItem().equals(item)) {
                cartItems.remove(cartItem);
                return;
            }
        }

        logger.exiting(cname, mname, cartItems.size());
    }

    public Float getTotal() {
        String mname = "getTotal";
        logger.entering(cname, mname);

        if (cartItems == null || cartItems.isEmpty())
            return 0f;

        Float total = 0f;

        // sum up the quantities
        for (CartItem cartItem : cartItems) {
            total += (cartItem.getSubTotal());
        }

        logger.exiting(cname, mname, total);
        return total;
    }

    public void empty() {
        String mname = "empty";
        logger.entering(cname, mname);

        cartItems.clear();
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
