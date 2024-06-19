package com.teste.gateway.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Sale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Product product;

	private Wallet payer;

	private Wallet payee;

	private BigDecimal price = BigDecimal.ZERO;

	private SaleStatus saleStatus = SaleStatus.Enum.CREATED.get();

	private LocalDateTime saleTime;

	private BigDecimal quantity;

	public Sale() {
	}

	public Sale(Product prod, Wallet payer2, Wallet payee2, BigDecimal price2, BigDecimal quantity2) {
		this.product = prod;
		this.payer = payer2;
		this.payee = payee2;
		this.price = price2;
		this.quantity = quantity2;
		this.saleTime = LocalDateTime.now();
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

	public Wallet getPayer() {
		return payer;
	}

	public void setPayer(Wallet payer) {
		this.payer = payer;
	}

	public Wallet getPayee() {
		return payee;
	}

	public void setPayee(Wallet payee) {
		this.payee = payee;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public SaleStatus getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(SaleStatus saleStatus) {
		this.saleStatus = saleStatus;
	}

	public LocalDateTime getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(LocalDateTime saleTime) {
		this.saleTime = saleTime;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

}
