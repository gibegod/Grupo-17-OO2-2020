package com.controlstock.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/* Problema: cuando actualizo un producto el createdat se transforma en null, y no se si eso deberia pasar. */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@NotNull
	private boolean status;
	
	//@NotNull
	private String description;
	
	//PrecioUnitario
	//@NotNull
	private float unitPrice;
	
	//Fecha de alta
	//@NotNull
	private LocalDate dischargeDate;
	
	//Marca
	//@NotNull
	private String brand;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	
	public Product() {}
	
	public Product(int id, boolean status, String description, float unitPrice, LocalDate dischargeDate,
			String brand) {
		this.id = id;
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
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	

	public boolean equals(Product product) {
	return product.getDescription().equalsIgnoreCase(description);
	}

	//public String toString(){
		//return id+"/"+status+"/"+description+"/"+precioUnitario+"/"+fechaAlta+"/"+b; 
	//}
	
	/*--------------------------------------------------*/
	
	/** 
	 *
	 * desactivarProducto
	 * 
	 * Se desactiva el Producto
	 *  El status del Producto cambia a "false"
	 * 
	 */

	//desactivarProducto
	public void deactivateProduct() {
		status = false;
	}
	
	/*--------------------------------------------------*/
	
	/** 
	 *
	 *activarProducto
	 * Se activa el Producto
	 *  El status del Producto cambia a "true"
	 * 
	 */
	
	//ActivarProducto
	public void activateProduct() {
		status = true;
	}
	
	/*--------------------------------------------------*/
}