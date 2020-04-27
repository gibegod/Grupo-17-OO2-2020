package com.controlstock.models.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	private boolean status;
	
	@NotNull
	private String descripcion;
	
	@NotNull
	private float precioUnitario;
	
	@NotNull
	private LocalDate fechaAlta;
	
	@NotNull
	private String marca;
	
	public Producto(int id, boolean status, String descripcion, float precioUnitario, LocalDate fechaAlta,
			String marca) {
		super();
		this.id = id;
		this.status = status;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.fechaAlta = fechaAlta;
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

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	
	public boolean equals(Producto producto) {
	return producto.getDescripcion().equalsIgnoreCase(descripcion);
	}

	public String toString(){
		return id+"/"+status+"/"+descripcion+"/"+precioUnitario+"/"+fechaAlta+"/"+marca; 
	}
	
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