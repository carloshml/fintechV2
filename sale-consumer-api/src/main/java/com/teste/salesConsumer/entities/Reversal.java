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
@Table(name = "tb_reversal")
public class Reversal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "sale_id")
	private Sale sale;

	@Column(name = "price")
	private BigDecimal price = BigDecimal.ZERO;

	private BigDecimal quantity;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;

	@Column(name = "created_time")
	private LocalDateTime createdTime;

	@Column(name = "exceuted_time")
	private LocalDateTime executedTime;

	public Reversal() {
	}

	public Reversal(Sale sale2, BigDecimal price2, BigDecimal quantity2) {
		this.sale = sale2;
		this.price = price2;
		this.quantity = quantity2;
		this.status = Status.Enum.CREATED.get();
		this.createdTime = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getExecutedTime() {
		return executedTime;
	}

	public void setExecutedTime(LocalDateTime executedTime) {
		this.executedTime = executedTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	
	
	

}
