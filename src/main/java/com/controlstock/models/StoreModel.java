package com.controlstock.models;

import java.util.Set;

public class StoreModel {

	private int id;
	
	private AddressModel address;

	private long phoneNumber;

	//private Employee manager;

	private Set<EmployeeModel> setEmployees;

	//private Set<Batch> setBatchs;

	public StoreModel() {}

	public StoreModel (int id, AddressModel address, long phoneNumber, Set<EmployeeModel> setEmployees) {
		super();
		setId(id);
		this.address = address;
		this.phoneNumber = phoneNumber;
		//this.manager = manager;
		this.setEmployees = setEmployees;
		//this.setBatchs = new HashSet<Batch>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/*public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}*/

	public Set<EmployeeModel> getSetEmployees() {
		return setEmployees;
	}

	public void setSetEmployees(Set<EmployeeModel> setEmployees) {
		this.setEmployees = setEmployees;
	}
	/*
	public Set<Batch> getSetBatchs() {
		return setBatchs;
	}

	public void setSetBatchs(Set<Batch> setBatchs) {
		this.setBatchs = setBatchs;
	}
*/

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoreModel other = (StoreModel) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
