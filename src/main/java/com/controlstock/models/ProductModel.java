package com.controlstock.models;

import java.time.LocalDate;

public class ProductModel {
	
	private int id;
	
	private boolean status;
	
	private String description;

	//Precio Unitario
	private float unitPrice;

	//private LocalDate fechaAlta;
	
	//Marca
	private String brand;
	
	public ProductModel() {}

	public ProductModel(int id, boolean status, String description, float unitPrice/*, LocalDate fechaAlta */,
			String brand) {
		super();
		this.setId(id);
		this.status = status;
		this.description = description;
		this.unitPrice = unitPrice;
		//this.fechaAlta = fechaAlta;
		this.brand = brand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
/*
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
*/
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
}
