package com.jpendon.WarehouseFrontEnd.domain.product.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jpendon.WarehouseFrontEnd.domain.product.model.Product;

@Service
public class ProductService implements IProductService {

	private final WebClient webClient; 
	
	public ProductService(WebClient.Builder builder) {
		webClient = builder.baseUrl("http://localhost:8080/api").build();
	}
	
	@Override
	public Product[] getProductList() {
		return webClient.get()
				.uri("/products/")
				.retrieve()
				.bodyToMono(Product[].class).block();
	}

	@Override
	public void saveProduct(Product product) {
		webClient.post()
			.uri("/products/")
			.bodyValue(product)
			.retrieve()
			.bodyToMono(Product.class).block();
	}
	
	@Override
	public Product getProductById(Long id) {
		return webClient.get()
			.uri("/products/" + id)
			.retrieve()
			.bodyToMono(Product.class).block();
	}

	@Override
	public void deleteProductById(Long id) {
		webClient.delete()
			.uri("/products/" + id)
			.retrieve()
			.bodyToMono(String.class).block();
		
	}

}
