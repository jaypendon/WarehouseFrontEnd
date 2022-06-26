package com.jpendon.WarehouseFrontEnd.domain.order.service;

import com.jpendon.WarehouseFrontEnd.domain.order.model.Order;

public interface IOrderService {

	public Order[] getAllOrders();
	
	public Order getOrderById(Long id);
	
	public void deleteOrderById(Long id);
	
	public void saveOrder(Order order);
}
