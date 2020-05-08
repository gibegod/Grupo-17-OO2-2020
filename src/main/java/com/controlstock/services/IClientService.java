package com.controlstock.services;

import java.util.List;

import com.controlstock.entities.Client;
import com.controlstock.models.ClientModel;

public interface IClientService {
	
	public List<Client> getAll();
	
	public ClientModel findById(int id);
	
	public ClientModel insertOrUpdate(ClientModel clientModel);
	
	public boolean remove(int id);

}
