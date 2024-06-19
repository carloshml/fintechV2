package com.teste.salesConsumer.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sale")
public class Sale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "wallet_payer_id")
	private Wallet payer;

	@Column(name = "price")
	private BigDecimal price = BigDecimal.ZERO;

	@ManyToOne
	@JoinColumn(name = "sale_status_id")
	private Status saleStatus = Status.Enum.CREATED.get();

	@Column(name = "sale_time")
	private LocalDateTime modifiedTime;

	private BigDecimal quantity;

	public Sale() {
	}

	public Sale(Product prod, Wallet payer2, BigDecimal price2, BigDecimal quantity2) {
		this.product = prod;
		this.payer = payer2;
		this.price = price2;
		this.quantity = quantity2;
		this.modifiedTime = LocalDateTime.now();
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Status getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(Status saleStatus) {
		this.modifiedTime = LocalDateTime.now();
		this.saleStatus = saleStatus;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

}
