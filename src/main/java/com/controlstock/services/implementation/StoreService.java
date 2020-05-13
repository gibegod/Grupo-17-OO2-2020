package com.controlstock.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.AddressConverter;
import com.controlstock.converters.StoreConverter;
import com.controlstock.entities.Address;
import com.controlstock.entities.Store;
import com.controlstock.models.AddressModel;
import com.controlstock.models.StoreModel;
import com.controlstock.repositories.IAddressRepository;
import com.controlstock.repositories.IStoreRepository;
import com.controlstock.services.IStoreService;

@Service("storeService")
public class StoreService implements IStoreService {

	@Autowired
	@Qualifier("storeRepository")
	private IStoreRepository storeRepository;

	@Autowired
	@Qualifier("storeConverter")
	private StoreConverter storeConverter;
	
	@Autowired
	@Qualifier("addressService")
	private AddressService addressService;
	
	@Autowired
	@Qualifier("addressRepository")
	private IAddressRepository addressRepository;
	
	@Autowired
	@Qualifier("addressConverter")
	private AddressConverter addressConverter;

	@Override
	public List<Store> getAll() {
		return storeRepository.findAll();
	}

	@Override
	public StoreModel insertOrUpdate(StoreModel storeModel) {
		Store store = storeRepository.save(storeConverter.modelToEntity(storeModel));
		return storeConverter.entityToModel(store);
	}
	
	@Override
	public StoreModel insert(StoreModel storeModel) {
		
		//Relaciono el id de address con todo el objeto address y lo seteo en storeModel.
		Address address = addressRepository.findById(storeModel.getAddress().getId());
		AddressModel addressModel = addressConverter.entityToModel(address);
		storeModel.setAddress(addressModel);
		
		Store store = storeRepository.save(storeConverter.modelToEntity(storeModel));
		return storeConverter.entityToModel(store);
	}
	
	@Override
	public StoreModel update(StoreModel storeModel) {
		storeModel.setAddress(addressService.findById(storeModel.getAddress().getId()));
		Store store = storeRepository.save(storeConverter.modelToEntity(storeModel));
		return storeConverter.entityToModel(store);
	}

	@Override
	public boolean remove(int id) {
		try {
			storeRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public StoreModel findById(int id) {
		return storeConverter.entityToModel(storeRepository.findById(id));
	}

}
