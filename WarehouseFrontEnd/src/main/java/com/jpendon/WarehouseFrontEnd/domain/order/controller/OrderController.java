package com.jpendon.WarehouseFrontEnd.domain.order.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jpendon.WarehouseFrontEnd.domain.order.dto.OrderedProductsDTO;
import com.jpendon.WarehouseFrontEnd.domain.order.model.Order;
import com.jpendon.WarehouseFrontEnd.domain.order.model.OrderedProducts;
import com.jpendon.WarehouseFrontEnd.domain.order.service.IOrderService;
import com.jpendon.WarehouseFrontEnd.domain.product.model.Product;
import com.jpendon.WarehouseFrontEnd.domain.product.service.IProductService;
import com.jpendon.WarehouseFrontEnd.domain.user.service.IUserService;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("")
	public String getAllOrders(Model model) {
		Order[] orders = orderService.getAllOrders();
		model.addAttribute("orders", orders);
		
		return "orders/listOrders";
	}
	
	@GetMapping("/orderedproduct/{orderId}")
	public String getOrderById(@PathVariable("orderId") Long id,Model model) {
		Order order = orderService.getOrderById(id);
		model.addAttribute("orders", order);
		
		return "orders/orderInfo";
	}
	
	@GetMapping("/delete/{orderId}")
	public String deleteOrderById(@PathVariable("orderId") Long id, Model model) {
		orderService.deleteOrderById(id);
		
		return "redirect:/orders";
	}
	
	
	@GetMapping("/createOrder/{userId}")
	public String showNewOrderForm(@PathVariable("userId") Long id, Model model) {
		Product[] productsList = productService.getProductList();
		OrderedProductsDTO orderedProductsDTO = new OrderedProductsDTO();
		model.addAttribute("products", productsList);
		model.addAttribute("orderedProductsDTO", orderedProductsDTO);
		
		return "orders/orderForm";
	}
	
	@PostMapping("/createOrder/{userId}/save")
	public String saveNewOrder(@PathVariable("userId") Long id, @ModelAttribute("orderedProductsDTO") OrderedProductsDTO orderedProductsDTO, Model model) {
		Order order = new Order();
			
		OrderedProducts orderedProduct = new OrderedProducts(productService.getProductById(orderedProductsDTO.getProductId()), orderedProductsDTO.getAmountOrdered());
		
		Set<OrderedProducts> orderedProductsList = new HashSet<OrderedProducts>();
		orderedProductsList.add(orderedProduct);
		
		order.setOrderedProducts(orderedProductsList);		
		order.setUser(userService.getUserById(id));
		
		orderService.saveOrder(order);
								
		return "redirect:/orders/";
	}
}
