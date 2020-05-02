package com.controlstock.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Direccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	private String ciudad;
	
	@NotNull
	private String calle;
	
	@NotNull
	private int numero;
	
	@NotNull
	private float latitud;
	
	@NotNull
	private float longitud;

	public Direccion(String ciudad, String calle, int numero, float latitud, float longitud) {
		super();
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public boolean equals(Direccion direccion) {
		return direccion.getLatitud() == latitud && direccion.getLongitud() == longitud;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	@Override
	public String toString() {
		return "Direccion [ciudad=" + ciudad + ", calle=" + calle + ", numero=" + numero + ", latitud=" + latitud
				+ ", longitud=" + longitud + "]";
	}

}
