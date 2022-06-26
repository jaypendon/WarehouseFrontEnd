package com.jpendon.WarehouseFrontEnd.domain.order.dto;

public class OrderedProductsDTO {
	
	private Long productId; 
	
	private Long amountOrdered; 
	
	private String productName; 
	
	public OrderedProductsDTO() {
		
	}

	public OrderedProductsDTO(Long productId, Long amountOrdered, String productName) {
		this.productId = productId;
		this.amountOrdered = amountOrdered;
		this.productName = productName;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	

}
