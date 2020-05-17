package com.controlstock.models;

import javax.validation.constraints.NotNull;
import com.controlstock.models.ProductModel;
import com.controlstock.models.EmployeeModel;

public class SaleRequestModel {
	

	private int id;
	

	private ProductModel product;
	
	private int amount;
	

	private EmployeeModel assistantEmployee;
	
	public SaleRequestModel(){};
	public SaleRequestModel(int id, ProductModel product, int amount, EmployeeModel assistantEmployee) {
		super();
		setId(id);
		this.product = product;
		this.amount = amount;
		this.assistantEmployee=assistantEmployee;
	}
	
	public SaleRequestModel(int id, ProductModel product, int amount) {
		super();
		setId(id);
		this.product = product;
		this.amount = amount;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public EmployeeModel getAssistantEmployee() {
		return assistantEmployee;
	}

	public void setAssistantEmployee(EmployeeModel assistantEmployee) {
		this.assistantEmployee = assistantEmployee;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	

}