package com.controlstock.models;

public class SaleRequestModel {

	private int id;
	
	private SaleModel sale;

	private ProductModel product;
	
	private int amount;
	
	private EmployeeModel assistantEmployee;
	
	public SaleRequestModel(){};
	
	//Constructor completo
	public SaleRequestModel(int id, SaleModel sale, ProductModel product, int amount, EmployeeModel assistantEmployee) {
		super();
		setId(id);
		this.sale = sale;
		this.product = product;
		this.amount = amount;
		this.assistantEmployee=assistantEmployee;
	}
	
	//Constructor sin empleado auxiliar
	public SaleRequestModel(int id, SaleModel sale, ProductModel product, int amount) {
		super();
		setId(id);
		this.sale = sale;
		this.product = product;
		this.amount = amount;
	}

	//Constructor sin sale
	public SaleRequestModel(int id, ProductModel product, int amount, EmployeeModel assistantEmployee) {
		super();
		setId(id);
		this.product = product;
		this.amount = amount;
		this.assistantEmployee=assistantEmployee;
	}
	
	//Constructor sin empleado auxiliar ni sale.
	public SaleRequestModel(int id, ProductModel product, int amount) {
		super();
		setId(id);
		this.product = product;
		this.amount = amount;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SaleModel getSale() {
		return sale;
	}

	public void setSale(SaleModel sale) {
		this.sale = sale;
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

}