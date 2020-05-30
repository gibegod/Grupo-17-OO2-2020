package com.controlstock.services.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.AddressConverter;
import com.controlstock.converters.EmployeeConverter;
import com.controlstock.converters.StoreConverter;
import com.controlstock.entities.Address;
import com.controlstock.entities.Batch;
import com.controlstock.entities.Product;
import com.controlstock.entities.Store;
import com.controlstock.helpers.DateBatchComparator;
import com.controlstock.models.AddressModel;
import com.controlstock.models.ProductModel;
import com.controlstock.models.StoreModel;
import com.controlstock.repositories.IAddressRepository;

import com.controlstock.repositories.IProductRepository;
import com.controlstock.repositories.IEmployeeRepository;
import com.controlstock.repositories.IBatchRepository;
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
	@Qualifier("batchService")
	private BatchService batchService;
	
	@Autowired
	@Qualifier("addressRepository")
	private IAddressRepository addressRepository;
	
	@Autowired
	@Qualifier("addressConverter")
	private AddressConverter addressConverter;
	
	@Autowired
	@Qualifier("employeeService")
	private EmployeeService employeeService;
	
	@Autowired
	@Qualifier("batchRepository")
	private IBatchRepository batchRepository;
	
	@Autowired
	@Qualifier("employeeRepository")
	private IEmployeeRepository employeeRepository;
	
	@Autowired
	@Qualifier("productRepository")
	private IProductRepository productRepository;
	
	@Autowired
	@Qualifier("employeeConverter")
	private EmployeeConverter employeeConverter;
	

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
	public List<Product> getProductsByStore(int id){
		Store store = storeRepository.findById(id);
		List<Product> products = new ArrayList<Product>();
		for(Batch b : store.getSetBatchs()) {
			products.add(b.getProduct());
		}
		return products;
	}
	
	public List<Store> getStoreByStock(int id, int amount){
		List<Store> storesProduct = getStoresByProductId(id);
		List<Store> stores = new  ArrayList<Store>();
		
		for(Store store: storesProduct) {
			if(amount <= getProductQuantity(store.getId(), id)) {
				stores.add(store);
			}
		}
		return stores;
		
	}
	@Override
	public int getProductQuantity(int idStore, int idProduct) {

		Product product = productRepository.findById(idProduct);
		
		int cantidad=0;
		for(int indice=0; indice<getActiveBatchs(idStore, idProduct).size(); indice++){
			if(this.getActiveBatchs(idStore, idProduct).get(indice).getProduct().getId()==product.getId()){
				cantidad += this.getActiveBatchs(idStore, idProduct).get(indice).getCurrentAmount();
			}
		}
		return cantidad;
	}
	
	@Override
	public List<Batch> getActiveBatchs(int idStore, int idProduct){
		Store store = storeRepository.findById(idStore);
		Product product = productRepository.findById(idProduct);
		List<Batch> batchs = new ArrayList<Batch>(store.getSetBatchs());
		for(Batch b : store.getSetBatchs()) {
			if (b.getCurrentAmount()>0 && b.getProduct().getId()==product.getId()) {
			batchs.add(b);
			}
		}
		return batchs;
	}
	
	 @Override
	public boolean validateStock (int idStore, int idProduct, int quantity) {
		return (quantity<=this.getProductQuantity(idStore,idProduct));
	}
	
	 @Override
	public void substractBatches(int idStore, int idProduct, int quantity) {
			Store store = storeRepository.findById(idStore);
			Product product = productRepository.findById(idProduct);
			
			int i=0;
			List<Batch> active = this.getActiveBatchs(idStore, idProduct);
			
			while(quantity > 0) {
					if(active.get(i).getCurrentAmount() > quantity) {
						active.get(i).setCurrentAmount(active.get(i).getCurrentAmount()-quantity);
						quantity=0;
					}
					else {
						quantity -= active.get(i).getCurrentAmount();
						active.get(i).setCurrentAmount(0);
					}
					batchRepository.save(active.get(i));
					i++;
				}
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
		
		Address address = addressRepository.findById(storeModel.getAddress().getId());
		AddressModel addressModel = addressConverter.entityToModel(address);
		storeModel.setAddress(addressModel);
		
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

	
	@Override
	public List<Store> getStoresByProductId (int id) {
		//Product product = productRepository.findById(id);
		List<Store> stores = new ArrayList<Store>();
		
		//Recorro todas las stores que hay en el service. Si esta store contiene el producto se guarda en stores.
		for (Store s : getAll()) {
			for(Batch b : s.getSetBatchs()) {
				if(b.getProduct().getId() == id) {
					stores.add(s);
				}
			}
		}
		return stores;
	}
}
