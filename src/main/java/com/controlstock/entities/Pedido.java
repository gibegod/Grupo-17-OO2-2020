package com.controlstock.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@OneToOne
	private Product producto;
	
	@NotNull
	private int cantidad;
	
	@OneToOne
	private Vendedor vendedorAuxiliar;
	
	public Pedido(Product producto, int cantidad, Vendedor vendedorAuxiliar) {
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
	public Vendedor getVendedorAuxiliar() {
		return vendedorAuxiliar;
	}
	public void setVendedorAuxiliar(Vendedor vendedorAuxiliar) {
		this.vendedorAuxiliar = vendedorAuxiliar;
	}

	@Override
	public String toString() {
		return "\n  Pedido [producto=" + producto + ", cantidad=" + cantidad + "]";
	}

}