package com.controlstock.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

@Entity
public class SaleRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Sale sale;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Product product;
	
	@NotNull
	private int amount;
	
	@Nullable
	@ManyToOne(optional = true, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Employee assistantEmployee;
	
	public SaleRequest(){};
	
	//Constructor completo
	public SaleRequest(int id, Sale sale, Product product, int amount, Employee assistantEmployee) {
		super();
		setId(id);
		this.sale = sale;
		this.product = product;
		this.amount = amount;
		this.assistantEmployee = assistantEmployee;
	}
	
	//Constructor sin empleado auxiliar.
	public SaleRequest(int id, Sale sale, Product product, int amount) {
		super();
		setId(id);
		this.sale = sale;
		this.product = product;
		this.amount = amount;
	}
	
	//Constructor sin sale.
	public SaleRequest(int id, Product product, int amount, Employee assistantEmployee) {
		super();
		setId(id);
		this.product = product;
		this.amount = amount;
		this.assistantEmployee=assistantEmployee;
	}
	
	//Constructor sin empleado auxiliar ni sale.
	public SaleRequest(int id, Product product, int amount) {
		super();
		setId(id);
		this.product = product;
		this.amount = amount;
	}
	
	
	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Employee getAssistantEmployee() {
		return assistantEmployee;
	}

	public void setAssistantEmployee(Employee assistantEmployee) {
		this.assistantEmployee = assistantEmployee;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	

}