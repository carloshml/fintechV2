package com.teste.gateway.entity;

import java.math.BigDecimal;

 
public class Product {

 
	private Long id;

	 
	private BigDecimal price = BigDecimal.ZERO;

	 
	private Wallet owner;

	private Integer quantity;

	private String name;

	public Product() {
	}

	public Product(BigDecimal value2, Wallet wallet, Integer quantity2, String name2) {
		this.price =  value2;	
		this.owner = wallet;
		this.quantity =  quantity2;
		this.name =  name2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	} 

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Wallet getOwner() {
		return owner;
	}

	public void setOwner(Wallet owner) {
		this.owner = owner;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
