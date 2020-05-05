package com.controlstock.converters;

import org.springframework.stereotype.Component;

import com.controlstock.entities.Client;
import com.controlstock.models.ClientModel;

@Component("clientConverter")
public class ClientConverter {

	public ClientModel entityToModel(Client client)
{
		return new ClientModel(client.getName(), client.getSurname(), client.getBirthdate(), client.getDni(), client.getMail());
}
	
//	public Client modelToEntity

}