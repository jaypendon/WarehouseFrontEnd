package com.jpendon.WarehouseFrontEnd.domain.order.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jpendon.WarehouseFrontEnd.domain.order.dto.OrderedProductsDTO;
import com.jpendon.WarehouseFrontEnd.domain.order.dto.OrderedProductsListDTO;
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
		OrderedProductsListDTO orderedProductsListDTO = new OrderedProductsListDTO();
		
		model.addAttribute("products", productsList);
		model.addAttribute("orderedProductsListDTO", orderedProductsListDTO);
		
		return "orders/orderForm";
	}
	
	@PostMapping(value="/createOrder/{userId}/save", params="action=save")
	public String saveNewOrder(@PathVariable("userId") Long id, @ModelAttribute("orderedProductsDTO") OrderedProductsListDTO orderedProductsListDTO, Model model, HttpServletRequest req) {
		
		Set<OrderedProducts> orderedProductsList = new HashSet<OrderedProducts>();
		
		orderedProductsList.add(
				new OrderedProducts(
						productService.getProductById(orderedProductsListDTO.getProductId()),
						orderedProductsListDTO.getAmountOrdered()		
						));
		
		OrderedProductsListDTO temp = (OrderedProductsListDTO) req.getSession().getAttribute("orderedProductsListDTO");
		Set<OrderedProductsDTO> previousOrders = temp.getOrderedProductsListDTO();
		
		for (OrderedProductsDTO orderedProductsDTO : previousOrders) {
			OrderedProducts orderedProduct = new OrderedProducts(
					productService.getProductById(orderedProductsDTO.getProductId()),
					orderedProductsDTO.getAmountOrdered());		
			
			orderedProductsList.add(orderedProduct);
		}		
		
		Order order = new Order();
		order.setOrderedProducts(orderedProductsList);		
		order.setUser(userService.getUserById(id));
		
		orderService.saveOrder(order);
		req.getSession().removeAttribute("orderedProductsListDTO");
								
		return "redirect:/orders/";
	}
	
	@PostMapping(value="/createOrder/{userId}/save", params="action=addOrderedProduct")
	public String saveOrderedProduct(@PathVariable("userId") Long id, @ModelAttribute("orderedProductsListDTO") OrderedProductsListDTO orderedProductsListDTO, Model model, HttpSession session) {		
		OrderedProductsListDTO previousOrders = (OrderedProductsListDTO)session.getAttribute("orderedProductsListDTO");
		
		OrderedProductsDTO newOrder = new OrderedProductsDTO(
				orderedProductsListDTO.getProductId(),
				orderedProductsListDTO.getAmountOrdered(),
				productService.getProductById(orderedProductsListDTO.getProductId()).getProductName()
				);
		
		if (previousOrders != null) {
			orderedProductsListDTO.getOrderedProductsListDTO().addAll(previousOrders.getOrderedProductsListDTO());			
		}
			
		orderedProductsListDTO.getOrderedProductsListDTO().add(newOrder);
		
		orderedProductsListDTO.clear();
		
		model.addAttribute("products", productService.getProductList());
	
		session.setAttribute("orderedProductsListDTO", orderedProductsListDTO);
		
		return "orders/orderForm";
	}

}
