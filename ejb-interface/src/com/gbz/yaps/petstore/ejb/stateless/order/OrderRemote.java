package com.gbz.yaps.petstore.ejb.stateless.order;

import java.util.List;

import javax.ejb.Remote;

import com.gbz.yaps.petstore.entity.order.Order;

@Remote
public interface OrderRemote {

    // ======================================
    // =             Constants              =
    // ======================================

    Order findOrder(Long orderId);

    void deleteOrder(Order order);

    List<Order> findOrders();
}
