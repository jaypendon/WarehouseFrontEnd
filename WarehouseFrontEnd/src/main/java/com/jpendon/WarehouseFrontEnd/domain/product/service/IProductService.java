package com.jpendon.WarehouseFrontEnd.domain.product.service;

import com.jpendon.WarehouseFrontEnd.domain.product.model.Product;

public interface IProductService {
	public Product[] getProductList();
	
	public void saveProduct(Product product);
	
	public Product getProductById(Long id);
	
	public void deleteProductById(Long id);
		
}
