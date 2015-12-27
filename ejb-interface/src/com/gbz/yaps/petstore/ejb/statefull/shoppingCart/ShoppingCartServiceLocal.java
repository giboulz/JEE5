package com.gbz.yaps.petstore.ejb.statefull.shoppingCart;

import java.util.List;

import javax.ejb.Local;

import com.gbz.yaps.petstore.dto.CartItem;
import com.gbz.yaps.petstore.entity.catalog.Item;

@Local
public interface ShoppingCartServiceLocal {

    // ======================================
    // =          Business methods          =
    // ======================================

    void addItem(Item item);

    void removeItem(Item item);

    Float getTotal();

    void empty();

    List<CartItem> getCartItems();
}
