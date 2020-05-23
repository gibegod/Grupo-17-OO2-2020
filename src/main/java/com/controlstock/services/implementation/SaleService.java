package com.controlstock.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.SaleConverter;
import com.controlstock.converters.SaleRequestConverter;
import com.controlstock.converters.StoreConverter;
import com.controlstock.converters.AddressConverter;
import com.controlstock.converters.ClientConverter;
import com.controlstock.converters.EmployeeConverter;
import com.controlstock.entities.Sale;
import com.controlstock.entities.SaleRequest;
import com.controlstock.entities.Store;
import com.controlstock.entities.Employee;
import com.controlstock.entities.Address;
import com.controlstock.entities.Client;
import com.controlstock.models.SaleModel;
import com.controlstock.models.SaleRequestModel;
import com.controlstock.models.StoreModel;
import com.controlstock.models.EmployeeModel;
import com.controlstock.models.AddressModel;
import com.controlstock.models.ClientModel;
import com.controlstock.repositories.ISaleRepository;
import com.controlstock.repositories.ISaleRequestRepository;
import com.controlstock.repositories.IStoreRepository;
import com.controlstock.repositories.IAddressRepository;
import com.controlstock.repositories.IClientRepository;
import com.controlstock.repositories.IEmployeeRepository;
import com.controlstock.services.ISaleService;

@Service("saleService")
public class SaleService implements ISaleService {
	
	@Autowired
	@Qualifier("saleRepository")
	private ISaleRepository saleRepository;
	
	@Autowired
	@Qualifier("saleConverter")
	private SaleConverter saleConverter;
	
	@Autowired
	@Qualifier("clientService")
	private ClientService clientService;
	
	@Autowired
	@Qualifier("clientRepository")
	private IClientRepository clientRepository;
	
	@Autowired
	@Qualifier("clientConverter")
	private ClientConverter clientConverter;

	@Autowired
	@Qualifier("employeeService")
	private EmployeeService employeeService;
	
	@Autowired
	@Qualifier("employeeRepository")
	private IEmployeeRepository employeeRepository;
	
	@Autowired
	@Qualifier("employeeConverter")
	private EmployeeConverter employeeConverter;
	
	@Autowired
	@Qualifier("storeRepository")
	private IStoreRepository storeRepository;
	
	@Autowired
	@Qualifier("storeConverter")
	private StoreConverter storeConverter;
	
	@Autowired
	@Qualifier("addressRepository")
	private IAddressRepository addressRepository;
	
	@Autowired
	@Qualifier("addressConverter")
	private AddressConverter addressConverter;
	
	@Override
	public List<Sale> getAll() {
		return saleRepository.findAll();
	}
	
	@Override
	public SaleModel insert(SaleModel saleModel) {
		
		//Si employee no es null
		if (saleModel.getEmployeeInCharge() != null) {
			Employee employee = employeeRepository.findById(saleModel.getEmployeeInCharge().getId());
			EmployeeModel employeeModel = employeeConverter.entityToModel(employee);
			saleModel.setEmployeeInCharge(employeeModel);
		}
		
		Store store = storeRepository.findById(saleModel.getEmployeeInCharge().getStore().getId());
		StoreModel storeModel = storeConverter.entityToModel(store);
		saleModel.setStoreModel(storeModel);
		
		Address address = addressRepository.findById(saleModel.getStoreModel().getAddress().getId());
		AddressModel addressModel = addressConverter.entityToModel(address);
		saleModel.getStoreModel().setAddress(addressModel);
		
		//Si cliente no es null.
		if (saleModel.getClient() != null) {
			Client client = clientRepository.findById(saleModel.getClient().getId());
			ClientModel clientModel = clientConverter.entityToModel(client);
			saleModel.setClient(clientModel);
		} 
		

		Sale sale = saleRepository.save(saleConverter.modelToEntity(saleModel));
		return saleConverter.entityToModel(sale);
	}
	
	@Override
	public SaleModel update(SaleModel saleModel) {
		
		if (saleModel.getClient() != null) {
			saleModel.setClient(clientService.findById(saleModel.getClient().getId()));
		}
		
		saleModel.setEmployeeInCharge(employeeService.findById(saleModel.getEmployeeInCharge().getId()));
		
		Sale sale = saleRepository.save(saleConverter.modelToEntity(saleModel));
		return saleConverter.entityToModel(sale);
	}
	
	@Override
	public boolean remove(int id) {
		try {
			saleRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public SaleModel findById(int id) {
		return saleConverter.entityToModel(saleRepository.findById(id));
	}
	
	//Busca entre todas las sales y devuelve la que es false (que esta en proceso).
	//No deberia ser una lista pero es para que no se rompa en el desarrollo.
	@Override
	public List<Sale> getSaleListByStatus() {
		List<Sale> sales = new ArrayList<Sale>();
		for (Sale sale : getAll()) {
			if(sale.getStatus() == false) {
				sales.add(sale);
			}
		}
		return sales;
	}

	@Override
	public Sale getSaleByStatus() {
		for (Sale sale : getAll()) {
			if(sale.getStatus() == false) {
				return sale;
			}
		}
		return null;
	}
	
}
