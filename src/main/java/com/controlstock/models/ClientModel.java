package com.controlstock.models;

import java.time.LocalDate;

import javax.validation.constraints.Email;

public class ClientModel extends PersonModel {
	
	@Email(message= "Please enter a valid email")
	private String mail;
	
	public ClientModel() {};

	public ClientModel(int id, String name, String surname, LocalDate birthdate, long dni, String mail) {
		super(id, name, surname, birthdate, dni);
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
