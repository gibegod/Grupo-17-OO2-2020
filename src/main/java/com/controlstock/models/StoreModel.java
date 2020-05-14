package com.controlstock.models;

import java.util.HashSet;
import java.util.Set;

import com.controlstock.entities.Employee;
import com.controlstock.entities.Batch;

public class StoreModel {

	private int id;
	
	private AddressModel address;

	private long phoneNumber;

	//private Employee manager;

	//private Set<Employee> setEmployees;

	//private Set<Batch> setBatchs;

	public StoreModel() {}

	public StoreModel (int id, AddressModel address, long phoneNumber) {
		super();
		setId(id);
		this.address = address;
		this.phoneNumber = phoneNumber;
		//this.manager = manager;
		//this.setEmployees = new HashSet<Employee>();
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
/*
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
	
	//Calculo distancia entre Stores
	public static float distanceStores(float lat1, float lng1, float lat2, float lng2) {
		float radioTierra = 6371; //en km.
		float dLat = (float) Math.toRadians(lat2 - lat1);
		float dLng = (float) Math.toRadians(lng2 - lng1);
		float sindLat = (float) Math.sin(dLat/2);
		float sindLng = (float) Math.sin(dLng/2);
		float va1 = (float) (Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(lat1)) *
					Math.cos(Math.toRadians(lat2)));
		float va2 = (float) (2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1)));
		
		return radioTierra * va2;
	}
	
	
	

}
