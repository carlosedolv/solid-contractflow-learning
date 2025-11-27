package com.carlosedolv.contractflow.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Installment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant date;
	private Double price;

	@ManyToOne
	@JoinColumn(name = "contract_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Contract contract;
	public Installment() {
	}

	public Installment(Long id, Instant date, Double price, Contract contract) {
		this.id = id;
		this.date = date;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, id, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Installment other = (Installment) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id) && Objects.equals(price, other.price);
	}
}
