package com.jpendon.WarehouseFrontEnd.domain.order.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jpendon.WarehouseFrontEnd.domain.user.model.User;

@Entity
@Table(name="orders")
public class Order {	
    private static final long serialVersionUID = 1L;
    
	@Id
	@SequenceGenerator(name="order_id", sequenceName = "order_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id")	
    @Column(name = "order_id")
	private Long orderId;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")	
	private Set<OrderedProducts> orderedProducts; 
	
	
	public Order() {
		this.orderedProducts = new HashSet<OrderedProducts>();
	}
	
	public Order(User user) {
		this.user = user;
	}

	public Order(User user, Set<OrderedProducts> orderedProducts) {
		this.user = user;
		this.orderedProducts = orderedProducts;
	}

	public Long getId() {
		return orderId;
	}

	public void setId(Long id) {
		this.orderId = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Set<OrderedProducts> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(Set<OrderedProducts> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	
}
