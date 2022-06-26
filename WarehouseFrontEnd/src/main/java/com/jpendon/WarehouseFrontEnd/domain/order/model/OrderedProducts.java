package com.jpendon.WarehouseFrontEnd.domain.order.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jpendon.WarehouseFrontEnd.domain.product.model.Product;

@Entity
@Table(name="ordered_products")
public class OrderedProducts {	
    private static final long serialVersionUID = 1L;
    
	@Id
	@SequenceGenerator(name="ordered_product_id", sequenceName = "ordered_product_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordered_product_id")	
    @Column(name = "ordered_product_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
	
	@Column(name="ordered_amount", nullable=false)
	private Long amountOrdered;	
	
	public OrderedProducts() {
		
	}

	public OrderedProducts(Product product, Long amountOrdered) {
		this.product = product;
		this.amountOrdered = amountOrdered;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	public Long getAmountOrdered() {
		return amountOrdered;
	}

	public void setAmountOrdered(Long amountOrdered) {
		this.amountOrdered = amountOrdered;
	}

	
}
	
	
