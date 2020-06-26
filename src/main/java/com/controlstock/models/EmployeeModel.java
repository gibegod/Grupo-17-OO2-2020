package com.controlstock.models;

import java.time.LocalDate;

public class EmployeeModel extends PersonModel {

	private int workingHours;

	private boolean manager;

	private float minimunWage;

	private float plus;

	private StoreModel store;

	public EmployeeModel() {}

	public EmployeeModel(int id, String name, String surname, LocalDate birthdate, long dni, int workingHours,
			boolean manager, float minimunWage, float plus, StoreModel store) {
		super(id, name, surname, birthdate, dni);
		this.workingHours = workingHours;
		this.manager = manager;
		this.minimunWage = minimunWage;
		this.plus = plus;
		this.store = store;
	}

	//Constructor que se usa en el set de Set<Employees>
	public EmployeeModel(int id, String name, String surname, LocalDate birthdate, long dni, int workingHours,
			boolean manager, float minimunWage, float plus) {
		super(id, name, surname, birthdate, dni);
		this.workingHours = workingHours;
		this.manager = manager;
		this.minimunWage = minimunWage;
		this.plus = plus;
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

	public StoreModel getStore() {
		return store;
	}

	public void setStore(StoreModel storeModel) {
		this.store = storeModel;
	}

}
