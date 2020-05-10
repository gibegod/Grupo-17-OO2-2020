package com.controlstock.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.AddressConverter;
import com.controlstock.entities.Address;
import com.controlstock.models.AddressModel;
import com.controlstock.repositories.IAddressRepository;
import com.controlstock.services.IAddressService;

@Service("addressService")
public class AddressService implements IAddressService {
	
	@Autowired
	@Qualifier("addressRepository")
	private IAddressRepository addressRepository;
	
	@Autowired
	@Qualifier("addressConverter")
	private AddressConverter addressConverter;
	
	@Override
	public List<Address> getAll() {
		return addressRepository.findAll();
	}
	
	@Override
	public AddressModel insertOrUpdate(AddressModel addressModel) {
		Address address = addressRepository.save(addressConverter.modelToEntity(addressModel));
		return addressConverter.entityToModel(address);
	}
	
	@Override
	public boolean remove(int id) {
		try {
			addressRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public AddressModel findById(int id) {
		return addressConverter.entityToModel(addressRepository.findById(id));
	}
	
}