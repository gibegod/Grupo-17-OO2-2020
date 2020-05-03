package com.controlstock.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class RankingProductos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@OneToOne
	private Product producto;
	
	@NotNull
	private int cantidad;
	
	public RankingProductos(Product producto, int cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}
	public Product getProducto() {
		return producto;
	}
	public void setProducto(Product producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "\nRankingProductos [producto=" + producto + ", cantidad=" + cantidad + "]";
	}
	
}