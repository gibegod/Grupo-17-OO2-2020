package com.controlstock.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.SaleRequestConverter;
import com.controlstock.converters.ProductConverter;
import com.controlstock.converters.SaleConverter;
import com.controlstock.converters.EmployeeConverter;
import com.controlstock.entities.SaleRequest;
import com.controlstock.entities.Product;
import com.controlstock.entities.Sale;
import com.controlstock.entities.Employee;
import com.controlstock.models.SaleRequestModel;
import com.controlstock.models.ProductModel;
import com.controlstock.models.SaleModel;
import com.controlstock.models.EmployeeModel;
import com.controlstock.repositories.ISaleRequestRepository;
import com.controlstock.repositories.IProductRepository;
import com.controlstock.repositories.ISaleRepository;
import com.controlstock.repositories.IEmployeeRepository;
import com.controlstock.services.ISaleRequestService;

@Service("saleRequestService")
public class SaleRequestService implements ISaleRequestService {

	@Autowired
	@Qualifier("saleRequestRepository")
	private ISaleRequestRepository saleRequestRepository;

	@Autowired
	@Qualifier("saleRequestConverter")
	private SaleRequestConverter saleRequestConverter;

	@Autowired
	@Qualifier("productService")
	private ProductService productService;

	@Autowired
	@Qualifier("productRepository")
	private IProductRepository productRepository;

	@Autowired
	@Qualifier("productConverter")
	private ProductConverter productConverter;

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
	@Qualifier("saleService")
	private SaleService saleService;

	@Autowired
	@Qualifier("saleConverter")
	private SaleConverter saleConverter;
	
	@Autowired
	@Qualifier("saleRepository")
	private ISaleRepository saleRepository;

	@Override
	public List<SaleRequest> getAll() {
		return saleRequestRepository.findAll();
	}

	@Override
	public SaleRequestModel insert(SaleRequestModel saleRequestModel) {

		Product product = productRepository.findById(saleRequestModel.getProduct().getId());
		ProductModel productModel = productConverter.entityToModel(product);
		saleRequestModel.setProduct(productModel);
		
		Sale sale = saleRepository.findById(saleRequestModel.getSale().getId());
		SaleModel saleModel = saleConverter.entityToModel(sale);
		saleRequestModel.setSale(saleModel);

		if (saleRequestModel.getAssistantEmployee() != null) {
			Employee employee = employeeRepository.findById(saleRequestModel.getAssistantEmployee().getId());
			EmployeeModel employeeModel = employeeConverter.entityToModel(employee);
			saleRequestModel.setAssistantEmployee(employeeModel);
		}

		// Se guarda el saleRequest en la bd.
		SaleRequest saleRequest = saleRequestConverter.modelToEntity(saleRequestModel);
		saleRequestRepository.save(saleRequest);

		// Seteo en el Set<SaleRequest> del sale correspondiente.
		saleRequest.getSale().getSetSaleRequests().add(saleRequest);
		saleService.update(saleConverter.entityToModel(saleRequest.getSale()));
		
		//System.out.println("ID sale: " + saleRequest.getSale().getId());

		// SaleRequest saleRequest = saleRequestRepository.save(saleRequestConverter.modelToEntity(saleRequestModel));
		return saleRequestConverter.entityToModel(saleRequest);
	}

	@Override
	public SaleRequestModel update(SaleRequestModel saleRequestModel) {

		saleRequestModel.setProduct(productService.findById(saleRequestModel.getProduct().getId()));

		if (saleRequestModel.getAssistantEmployee() != null) {
			saleRequestModel
					.setAssistantEmployee(employeeService.findById(saleRequestModel.getAssistantEmployee().getId()));
		}

		SaleRequest saleRequest = saleRequestRepository.save(saleRequestConverter.modelToEntity(saleRequestModel));
		return saleRequestConverter.entityToModel(saleRequest);
	}

	@Override
	public boolean remove(int id) {
		try {
			saleRequestRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public SaleRequestModel findById(int id) {
		return saleRequestConverter.entityToModel(saleRequestRepository.findById(id));
	}

}