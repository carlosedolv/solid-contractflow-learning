package com.carlosedolv.contractflow.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "installments")
public class Installment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "contract_id")
	@JsonIgnore
	private Contract contract;
	public Installment() {
	}

	public Installment(Long id, LocalDate date, BigDecimal price, Contract contract) {
		this.id = id;
		this.date = date;
		this.price = price;
        this.contract = contract;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

    public Contract getContract() {
        return contract;
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
