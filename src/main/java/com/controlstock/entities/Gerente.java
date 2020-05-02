package com.controlstock.entities;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Gerente")
public class Gerente extends Empleado{

	public Gerente(String nombre, String apellido, LocalDate fechaNacimiento, long dni, int horasPorJornada,
			float sueldoBasico) {
		super(nombre, apellido, fechaNacimiento, dni, horasPorJornada, sueldoBasico);
	}

	@Override
	public String toString() {
		return "Gerente [horasPorJornada=" + horasPorJornada + ", sueldoBasico=" + sueldoBasico + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", dni=" + dni + "]";
	}

	
}