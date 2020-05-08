package com.controlstock.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class PersonModel {
	
	protected int id;
    
	protected String name;
    
	protected String surname;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd") //Sirve para que funcione el type="date" del input del HTML.
	protected LocalDate birthdate;
	
    private long dni;
	
	public PersonModel() {}
	
	public PersonModel(int id, String name, String surname, LocalDate birthdate, long dni) {
		super();
		this.setId(id);
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.dni = dni;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public long getDni() {
		return dni;
	}
	public void setDni(long dni) {
		this.dni = dni;
	}

}
