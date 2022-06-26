package com.jpendon.WarehouseFrontEnd.domain.order.dto;

import java.util.HashSet;
import java.util.Set;

public class OrderedProductsListDTO {

	private Set<OrderedProductsDTO> orderedProductsListDTO; 
	
	private Long productId; 
	
	private Long amountOrdered; 
	
	public OrderedProductsListDTO() {
		this.orderedProductsListDTO = new HashSet<OrderedProductsDTO>();
	}

	public Set<OrderedProductsDTO> getOrderedProductsListDTO() {
		return orderedProductsListDTO;
	}

	public void setOrderedProductsListDTO(Set<OrderedProductsDTO> orderedProductsListDTO) {
		this.orderedProductsListDTO = orderedProductsListDTO;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getAmountOrdered() {
		return amountOrdered;
	}

	public void setAmountOrdered(Long amountOrdered) {
		this.amountOrdered = amountOrdered;
	}
	
	public void clear() {
		productId = null;
		amountOrdered = null;
	}
}
