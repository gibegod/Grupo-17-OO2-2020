package com.controlstock.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ProductModel {
	
	private int id;
	
	private boolean status;
	
	private String description;

	//Precio Unitario
	private float unitPrice;
	
	//FechaAlta
	@DateTimeFormat(pattern = "yyyy-MM-dd") //Sirve para que funcione el type="date" del input del HTML.
	private LocalDate dischargeDate;
	
	//Marca
	private String brand;
	
	public ProductModel() {}

	public ProductModel(int id, boolean status, String description, float unitPrice, LocalDate dischargeDate ,
			String brand) {
		this.setId(id);
		this.status = status;
		this.description = description;
		this.unitPrice = unitPrice;
		this.dischargeDate = dischargeDate;
		this.brand = brand;
	}

	public int getId() {
		return id;
	}

	//Tiene que ser public
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

	public LocalDate getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(LocalDate dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
}
