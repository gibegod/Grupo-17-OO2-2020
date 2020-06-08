package com.controlstock.models;

public class ProductRankingModel {

	private int id;
	
	private ProductModel product;
	
	private int amount;
	
	public ProductRankingModel(){};
	
	public ProductRankingModel(int id, ProductModel product, int amount) {
		super();
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
