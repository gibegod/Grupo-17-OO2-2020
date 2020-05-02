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
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@NotNull
	private boolean status;
	
	//@NotNull
	private String descripcion;
	
	//@NotNull
	private float precioUnitario;
	
	//@NotNull
	//private LocalDate fechaAlta;
	
	//@NotNull
	private String marca;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	
	public Producto() {}
	
	public Producto(int id, boolean status, String descripcion, float precioUnitario/*, LocalDate fechaAlta*/,
			String marca) {
		super();
		this.id = id;
		this.status = status;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		//this.fechaAlta = fechaAlta;
		this.marca = marca;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
/*
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
*/
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
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
	
	
	

	public boolean equals(Producto producto) {
	return producto.getDescripcion().equalsIgnoreCase(descripcion);
	}

	//public String toString(){
		//return id+"/"+status+"/"+descripcion+"/"+precioUnitario+"/"+fechaAlta+"/"+marca; 
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

	public void desactivarProducto() {
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
	
	public void activarProducto() {
		status = true;
	}
	
	/*--------------------------------------------------*/

}