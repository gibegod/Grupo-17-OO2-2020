package com.controlstock.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.SaleConverter;
import com.controlstock.converters.SaleRequestConverter;
import com.controlstock.converters.ClientConverter;
import com.controlstock.converters.EmployeeConverter;
import com.controlstock.entities.Sale;
import com.controlstock.entities.SaleRequest;
import com.controlstock.entities.Employee;
import com.controlstock.entities.Client;
import com.controlstock.models.SaleModel;
import com.controlstock.models.SaleRequestModel;
import com.controlstock.models.EmployeeModel;
import com.controlstock.models.ClientModel;
import com.controlstock.repositories.ISaleRepository;
import com.controlstock.repositories.ISaleRequestRepository;
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
	
	@Override
	public List<Sale> getAll() {
		return saleRepository.findAll();
	}
	
	@Override
	public SaleModel insert(SaleModel saleModel) {
		
		Client client = clientRepository.findById(saleModel.getClient().getId());
		ClientModel clientModel = clientConverter.entityToModel(client);

		saleModel.setClient(clientModel);
		
		Employee employee = employeeRepository.findById(saleModel.getEmployeeInCharge().getId());
		EmployeeModel employeeModel = employeeConverter.entityToModel(employee);
		saleModel.setEmployeeInCharge(employeeModel);
		
		
		Sale sale = saleRepository.save(saleConverter.modelToEntity(saleModel));
		return saleConverter.entityToModel(sale);
	}
	
	@Override
	public SaleModel update(SaleModel saleModel) {
		
		saleModel.setClient(clientService.findById(saleModel.getClient().getId()));
		
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

}
