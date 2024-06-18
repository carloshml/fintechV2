package com.teste.fintech.entity;

import java.math.BigDecimal;
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
@Table(name = "tb_wallet_statement")
public class WalletStatement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "wallet_id")
	private Wallet wallet;

	@ManyToOne
	@JoinColumn(name = "wallet_action")
	private WalletAction action;

	private BigDecimal previousBalance;

	private BigDecimal currentBalance;

	private BigDecimal value;

	@Column(name = "transfer_time")
	private LocalDateTime statementTime;

	public WalletStatement() {
	}
	
	public WalletStatement(Wallet w, WalletAction wa, BigDecimal pb, BigDecimal cb, BigDecimal vl) {	 
		this.wallet = w;
		this.action = wa;
		this.previousBalance= pb;
		this.currentBalance= cb;
		this.value = vl;
		this.statementTime = LocalDateTime.now();
	} 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	} 

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public WalletAction getAction() {
		return action;
	}

	public void setAction(WalletAction action) {
		this.action = action;
	}

	public BigDecimal getPreviousBalance() {
		return previousBalance;
	}

	public void setPreviousBalance(BigDecimal previousBalance) {
		this.previousBalance = previousBalance;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public LocalDateTime getStatementTime() {
		return statementTime;
	}

	public void setStatementTime(LocalDateTime statementTime) {
		this.statementTime = statementTime;
	}
	
	

}
