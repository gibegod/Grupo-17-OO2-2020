package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.Address;
import com.controlstock.models.AddressModel;

public interface IAddressService {
	
	public List<Address> getAll();
	
	public AddressModel findById(int id);
	
	public AddressModel insertOrUpdate(AddressModel addressModel);
	
	public boolean remove(int id);

}
