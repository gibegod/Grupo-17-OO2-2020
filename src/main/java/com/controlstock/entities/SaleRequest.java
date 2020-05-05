package com.controlstock.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class SaleRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@OneToOne
	private Product product;
	
	@NotNull
	private int amount;
	
	@OneToOne
	private Employee assistantEmployee;
	public SaleRequest(){};
	public SaleRequest(Product product, int amount) {
		super();
		this.product = product;
		this.amount = amount;
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
	
	

}