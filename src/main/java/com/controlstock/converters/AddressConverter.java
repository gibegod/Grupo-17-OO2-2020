package com.controlstock.converters;

import org.springframework.stereotype.Component;
import com.controlstock.entities.Address;
import com.controlstock.models.AddressModel;

/* Descripcion: Pasa de model a entity y de entity a model. */

@Component("addressConverter")
public class AddressConverter {
	
	public AddressModel entityToModel(Address address) {
		return new AddressModel(address.getId(), address.getCity(), address.getStreet(), address.getNumber(), 
								address.getLatitude(), address.getLongitude());
	}
	
	public Address modelToEntity(AddressModel addressModel) {
		return new Address(addressModel.getId(), addressModel.getCity(), addressModel.getStreet(), 
							addressModel.getNumber(), addressModel.getLatitude(), addressModel.getLongitude());
	}
	
}