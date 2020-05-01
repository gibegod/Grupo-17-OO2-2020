package com.controlstock.entities;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "Cliente")
public class Cliente extends Persona {
	
	@NotNull
	private String mail;

	public Cliente(String nombre, String apellido, LocalDate fechaNacimiento, long dni, String mail) {
		super(nombre, apellido, fechaNacimiento, dni);
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento
				+ ", mail=" + mail + ", dni=" + dni + "]";
	}
}
