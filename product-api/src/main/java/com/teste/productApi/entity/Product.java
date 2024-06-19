package com.teste.productApi.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "price")
	private BigDecimal price = BigDecimal.ZERO;

	@ManyToOne
	@JoinColumn(name = "wallet_id")
	private Wallet owner;

	private Integer quantity;

	@Column(name = "name", unique = true)
	private String name;

	public Product() {
	}

	public Product(BigDecimal value2, Wallet wallet, Integer quantity2, String name2) {
		this.price = value2;
		this.owner = wallet;
		this.quantity = quantity2;
		this.name = name2;
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
