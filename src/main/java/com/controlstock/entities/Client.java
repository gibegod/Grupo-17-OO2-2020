package com.controlstock.entities;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "Client")
public class Client extends Person {
	
	@NotNull
	private String mail;
	public Client(){};
	public Client(String name, String surname, LocalDate birthdate, long dni, String mail) {
		super(name, surname, birthdate, dni);
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	/*@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento
				+ ", mail=" + mail + ", dni=" + dni + "]";
	}*/
}
