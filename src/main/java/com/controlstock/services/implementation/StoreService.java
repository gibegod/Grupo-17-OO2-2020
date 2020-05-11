package com.controlstock.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.StoreConverter;
import com.controlstock.entities.Store;
import com.controlstock.models.StoreModel;
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
		Store store  = storeRepository.save(storeConverter.modelToEntity(storeModel));
		return storeConverter.entityToModel(store);
	}
	
	@Override
	public StoreModel Update(StoreModel storeModel) {
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
