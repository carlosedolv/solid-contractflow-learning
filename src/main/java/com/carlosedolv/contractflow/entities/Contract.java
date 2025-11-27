package com.carlosedolv.contractflow.entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Contract implements Serializable {
    @Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int number;
	private Instant date;
	private Double total;
	private int installmentQuantity;

	@OneToMany(mappedBy = "contract")
	private List<Installment> installments = new ArrayList<>();
	
	public Contract() {
	}

	public Contract(Long id, int number, Instant date, Double total, int installmentQuantity) {
		super();
		this.id = id;
		this.number = number;
		this.date = date;
		this.total = total;
		this.installmentQuantity = installmentQuantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public int getInstallmentQuantity() {
		return installmentQuantity;
	}

	public void setInstallmentQuantity(int installmentQuantity) {
		this.installmentQuantity = installmentQuantity;
	}

	public List<Installment> getInstallments() {
		return installments;
	}

	public void setInstallments(List<Installment> installments) {
		this.installments = installments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, id, installmentQuantity, installments, number, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contract other = (Contract) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id)
				&& installmentQuantity == other.installmentQuantity && Objects.equals(installments, other.installments)
				&& number == other.number && Objects.equals(total, other.total);
	}

}
