package com.controlstock.entities;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "Vendedor")
public class Vendedor extends Empleado {
	
	@NotNull
	private float plus;
	
	@NotNull
	@OneToOne
	private Sucursal sucursal;

	public Vendedor(String nombre, String apellido, LocalDate fechaNacimiento, long dni, int horasPorJornada,
			float sueldoBasico, Sucursal sucursal) {
		super(nombre, apellido, fechaNacimiento, dni, horasPorJornada, sueldoBasico);
		this.plus = 0;
		this.sucursal = sucursal;
	}

	public float getPlus() {
		return plus;
	}

	public void setPlus(float plus) {
		this.plus = plus;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public String toString() {
		return "Vendedor [nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento
				+ ", dni=" + dni + ", plus=" + plus + ", horasPorJornada=" + horasPorJornada + ", sueldoBasico="
				+ sueldoBasico + "]";
	}
}
