package com.controlstock.entities;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "Employee")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee extends Person{
	
	@NotNull
	protected int workingHours;
	
	@NotNull
	public boolean manager;
	@NotNull
	protected float minimunWage;
	
	@NotNull
	private float plus;
	
	@NotNull
	@OneToOne
	private Store store;
	
	public Employee() {};
	
	public Employee(int id, String name, String surname, LocalDate birthdate, long dni, 
			int workingHours, float minimunWage, boolean manager, float plus, Store store) {
		super(id, name, surname, birthdate, dni);
		this.workingHours = workingHours;
		this.minimunWage = minimunWage;
		this.manager = manager;
		this.plus = plus;
		this.store = store;
	}

	public int getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(int workingHours) {
		this.workingHours = workingHours;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	public float getMinimunWage() {
		return minimunWage;
	}

	public void setMinimunWage(float minimunWage) {
		this.minimunWage = minimunWage;
	}

	public float getPlus() {
		return plus;
	}

	public void setPlus(float plus) {
		this.plus = plus;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	

}