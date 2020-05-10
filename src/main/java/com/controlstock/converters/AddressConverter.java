package com.controlstock.converters;

import org.springframework.stereotype.Component;
import com.controlstock.entities.Address;
import com.controlstock.models.AddressModel;

/* Descripcion: Pasa de model a entity y de entity a model. */

@Component("addressConverter")
public class AddressConverter {
	
	public AddressModel entityToModel(Address address) {
		return new AddressModel(address.getId(), address.getCity(), address.getStreet(),
				address.getNumber(), address.getLatitude(), address.getLongitude());
	}
	
	public Address modelToEntity(AddressModel addressmodel) {
		return new Address(addressmodel.getId(), addressmodel.getCity(), addressmodel.getStreet(),
				addressmodel.getNumber(), addressmodel.getLatitude(), addressmodel.getLongitude());
	}
	
}