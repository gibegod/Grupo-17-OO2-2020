package com.controlstock.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class BatchModel {
	
	private int id;
	
	private ProductModel product;
	
	private char size;
	
	private int currentAmount;
	
	private int initialAmount;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate admissionDate;
	
	
	public BatchModel() {}


	public BatchModel(int id, ProductModel product, char size, int currentAmount, int initialAmount,
			LocalDate admissionDate) {
		super();
		this.id = id;
		this.product = product;
		this.size = size;
		this.currentAmount = currentAmount;
		this.initialAmount = initialAmount;
		this.admissionDate = admissionDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public ProductModel getProduct() {
		return product;
	}


	public void setProduct(ProductModel product) {
		this.product = product;
	}


	public char getSize() {
		return size;
	}


	public void setSize(char size) {
		this.size = size;
	}


	public int getCurrentAmount() {
		return currentAmount;
	}


	public void setCurrentAmount(int currentAmount) {
		this.currentAmount = currentAmount;
	}


	public int getInitialAmount() {
		return initialAmount;
	}


	public void setInitialAmount(int initialAmount) {
		this.initialAmount = initialAmount;
	}


	public LocalDate getAdmissionDate() {
		return admissionDate;
	}


	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	
}