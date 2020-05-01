package com.controlstock.entities;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Empleado extends Persona{
	
	@NotNull
	protected int horasPorJornada;
	
	@NotNull
	protected float sueldoBasico;
	
	public Empleado(String nombre, String apellido, LocalDate fechaNacimiento, long dni, int horasPorJornada,
			float sueldoBasico) {
		super(nombre, apellido, fechaNacimiento, dni);
		this.horasPorJornada = horasPorJornada;
		this.sueldoBasico = sueldoBasico;
	}

	public int getHorasPorJornada() {
		return horasPorJornada;
	}


	public void setHorasPorJornada(int horasPorJornada) {
		this.horasPorJornada = horasPorJornada;
	}


	public float getSueldoBasico() {
		return sueldoBasico;
	}


	public void setSueldoBasico(float sueldoBasico) {
		this.sueldoBasico = sueldoBasico;
	}

}