package com.controlstock.models;

import java.time.LocalDate;

import com.controlstock.entities.Persona;

public class ClientModel extends Persona {
	
	private String mail;
	
	public ClientModel() {};

	public ClientModel(String nombre, String apellido, LocalDate fechaNacimiento, long dni, String mail) {
		super(nombre, apellido, fechaNacimiento, dni);
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
