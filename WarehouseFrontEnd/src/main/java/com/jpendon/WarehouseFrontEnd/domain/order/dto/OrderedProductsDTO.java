package com.jpendon.WarehouseFrontEnd.domain.order.dto;

public class OrderedProductsDTO {

	private Long productId; 
	
	private Long amountOrdered; 
	
	public OrderedProductsDTO() {
		
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
	
	
}
