package com.controlstock.models;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.controlstock.entities.Address;
import com.controlstock.entities.Employee;
import com.controlstock.entities.Batch;
import com.controlstock.entities.Store;

public class StoreModel {

private int id;
	
	private Address address;

	private long phoneNumber;

	private Employee manager;

	private Set <Employee> setEmployees;

	private Set<Batch> setBatchs;

	public StoreModel() {}

	public StoreModel (Address address, int id, long phoneNumber, Employee manager) {
		super();
		this.address = address;
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.manager = manager;
		this.setEmployees = new HashSet<Employee>();
		this.setBatchs = new HashSet<Batch>();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Set<Employee> getSetEmployees() {
		return setEmployees;
	}

	public void setSetEmployees(Set<Employee> setEmployees) {
		this.setEmployees = setEmployees;
	}

	public Set<Batch> getSetBatchs() {
		return setBatchs;
	}

	public void setSetBatchs(Set<Batch> setBatchs) {
		this.setBatchs = setBatchs;
	}

	public int getId() {
		return id;
	}

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
