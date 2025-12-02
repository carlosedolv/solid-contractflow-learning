package com.carlosedolv.contractflow.entities;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.carlosedolv.contractflow.entities.enums.PaymentType;
import jakarta.persistence.*;

@Entity
@Table(name = "contracts")
public class Contract implements Serializable {
    @Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int number;
	private LocalDate date;
	private BigDecimal total;
	private int installmentQuantity;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

	@OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Installment> installments = new ArrayList<>();
	
	public Contract() {
	}

	public Contract(Long id, int number, LocalDate date, BigDecimal total, int installmentQuantity, PaymentType paymentType) {
		super();
		this.id = id;
		this.number = number;
		this.date = date;
		this.total = total;
		this.installmentQuantity = installmentQuantity;
        this.paymentType = paymentType;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public int getInstallmentQuantity() {
		return installmentQuantity;
	}

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
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
				&& installmentQuantity == other.installmentQuantity && paymentType == other.paymentType && Objects.equals(installments, other.installments)
				&& number == other.number && Objects.equals(total, other.total);
	}

}
