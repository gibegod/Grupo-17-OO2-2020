package com.controlstock.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Lote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Producto producto;
	
	@NotNull
	private char talle;
	
	@NotNull
	private int cantidadActual;
	
	@NotNull
	private int cantidadInicial;
	
	@NotNull
	private LocalDate fechaIngreso;
	
	
	public Lote(Producto producto, char talle, int cantidadInicial, LocalDate fechaIngreso) {
		super();
		this.producto = producto;
		this.talle = talle;
		this.cantidadActual = cantidadInicial;
		this.cantidadInicial = cantidadInicial;
		this.fechaIngreso = fechaIngreso;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public char getTalle() {
		return talle;
	}


	public void setTalle(char talle) {
		this.talle = talle;
	}


	public int getCantidadActual() {
		return cantidadActual;
	}


	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}


	public int getCantidadInicial() {
		return cantidadInicial;
	}


	public void setCantidadInicial(int cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}


	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	@Override
	public String toString() {
		return "\nLote [producto=" + producto + ", talle=" + talle + ", cantidadActual=" + cantidadActual
				+ ", cantidadInicial=" + cantidadInicial + ", fechaIngreso=" + fechaIngreso + "]";
	}

}