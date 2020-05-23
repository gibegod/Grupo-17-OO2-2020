	package com.controlstock.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.ClientConverter;
import com.controlstock.entities.Client;
import com.controlstock.models.ClientModel;
import com.controlstock.repositories.IClientRepository;
import com.controlstock.services.IClientService;

@Service("clientService")
public class ClientService implements IClientService {
	
	@Autowired
	@Qualifier("clientRepository")
	private IClientRepository clientRepository;
	
	@Autowired
	@Qualifier("clientConverter")
	private ClientConverter clientConverter;
	
	@Override
	public List<Client> getAll() {
		return clientRepository.findAll();
	}
	
	@Override
	public ClientModel insertOrUpdate(ClientModel clientModel) {
		Client client = clientRepository.save(clientConverter.modelToEntity(clientModel));
		return clientConverter.entityToModel(client);
	}
	
	@Override
	public boolean remove(int id) {
		try {
			clientRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public ClientModel findById(int id) {
		return clientConverter.entityToModel(clientRepository.findById(id));
	}
	
}