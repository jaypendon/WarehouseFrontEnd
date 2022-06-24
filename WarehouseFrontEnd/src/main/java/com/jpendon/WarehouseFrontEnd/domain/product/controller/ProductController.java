package com.jpendon.WarehouseFrontEnd.domain.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jpendon.WarehouseFrontEnd.domain.product.model.Product;
import com.jpendon.WarehouseFrontEnd.domain.product.service.IProductService;
import com.jpendon.WarehouseFrontEnd.domain.user.model.User;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired 
	private IProductService productService; 
	
	@GetMapping("")
	public String getProductsList(Model model) {
		Product[] productList = productService.getProductList();
		model.addAttribute("products", productList);
		
		return "products/listProducts"; 
	}
	
	@GetMapping("/newProduct")
	public String showNewProductForm(Model model) {
		Product product = new Product(); 
		
		model.addAttribute("product", product);
		
		return "products/productForm"; 
	}
	
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute("product") Product product) {
		productService.saveProduct(product);
		
		return "redirect:/products";
	}
	
	@GetMapping("/update")
	public String showUpdateForm(@RequestParam("userId") Long id, Model model) {
		Product product = productService.getProductById(id);
		
		model.addAttribute("product", product);
		
		return "products/productForm";
	}
	
	@GetMapping("/delete")
	public String deleteProductById(@RequestParam("productId") Long id) {
		productService.deleteProductById(id);
		
		return "redirect:/products";
	}
}


