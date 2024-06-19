package com.teste.gateway.entity;

import java.util.Objects;
 
public class SaleStatus {

 
	private Long id;

	private String description;

	public SaleStatus() {
	}

	public SaleStatus(Long id, String description) {
		this.id = id;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public enum Enum {

		CREATED(1L, "created"),
		PROCESSING(1L, "processing"),
		DELIVERED(2L, "delivered");

		private Enum(Long id, String description) {
			this.id = id;
			this.description = description;
		}

		private Long id;
		private String description;

		public SaleStatus get() {
			return new SaleStatus(id, description);
		}

	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaleStatus other = (SaleStatus) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}

}
