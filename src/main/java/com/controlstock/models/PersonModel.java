package com.controlstock.models;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class PersonModel {
	
	protected int id;
    
	@Size(min= 3, message= "Name must have at least 3 characters")
	protected String name;
    
	@Size(min= 3, message= "Surname must have at least 3 characters")
	protected String surname;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd") //Sirve para que funcione el type="date" del input del HTML.
	protected LocalDate birthdate;
	
	@Min(value = 10000000, message= "Description must have 8 characters")
	@Max(value = 99999999, message= "Description must have 8 characters")
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
