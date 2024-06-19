package com.teste.salesConsumer.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_status")
public class Status implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	public Status() {
	}

	public Status(Long id, String description) {
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
		PROCESSING(2L, "processing"),
		DELIVERED(3L, "delivered"),
		CANCELED(4L, "canceled "),
		EXECUTED(5L, "executed");

		private Enum(Long id, String description) {
			this.id = id;
			this.description = description;
		}

		private Long id;
		private String description;

		public Status get() {
			return new Status(id, description);
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
		Status other = (Status) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}

}
