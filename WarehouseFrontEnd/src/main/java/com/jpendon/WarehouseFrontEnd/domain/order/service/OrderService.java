package com.jpendon.WarehouseFrontEnd.domain.order.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jpendon.WarehouseFrontEnd.domain.order.model.Order;


@Service
public class OrderService implements IOrderService{

	private final WebClient webClient; 
	
	public OrderService(WebClient.Builder builder) {
		webClient = builder.baseUrl("http://localhost:8080/api").build();
	}
	
	public Order[] getAllOrders() {
		return webClient.get()
				.uri("/orders/")
				.retrieve()
				.bodyToMono(Order[].class).block();
	}
	
	public Order getOrderById(Long id) {
		return webClient.get()
				.uri("/orders/" + id)
				.retrieve()
				.bodyToMono(Order.class).block();
	}

	@Override
	public void deleteOrderById(Long id) {
		webClient.delete()
			.uri("/orders/" + id)
			.retrieve()
			.bodyToMono(String.class).block();
		
	}
	
	@Override
	public void saveOrder(Order order) {
		webClient.post()
			.uri("/orders/")
			.bodyValue(order)
			.retrieve()
			.bodyToMono(Order.class).block();
	}
}
