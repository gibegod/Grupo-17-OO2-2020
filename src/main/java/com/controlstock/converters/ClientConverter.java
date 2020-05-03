package com.controlstock.converters;

import org.springframework.stereotype.Component;

import com.controlstock.entities.Cliente;
import com.controlstock.models.ClientModel;

@Component("clientConverter")
public class ClientConverter {

	public ClientModel entityToModel(Cliente client)
{
		return new ClientModel(client.getNombre(), client.getApellido(), client.getFechaNacimiento(), client.getDni(), client.getMail());
}
	
//	public Client modelToEntity

}