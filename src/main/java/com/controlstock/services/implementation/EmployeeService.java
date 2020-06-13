package com.controlstock.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.EmployeeConverter;
import com.controlstock.converters.StoreConverter;
import com.controlstock.entities.Employee;
import com.controlstock.entities.Sale;
import com.controlstock.entities.Store;
import com.controlstock.models.EmployeeModel;
import com.controlstock.models.SaleModel;
import com.controlstock.models.SaleRequestModel;
import com.controlstock.models.StoreModel;
import com.controlstock.repositories.IEmployeeRepository;
import com.controlstock.repositories.IStoreRepository;
import com.controlstock.services.IEmployeeService;

@Service("employeeService")
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	@Qualifier("employeeRepository")
	private IEmployeeRepository employeeRepository;
	
	@Autowired
	@Qualifier("employeeConverter")
	private EmployeeConverter employeeConverter;
	
	@Autowired
	@Qualifier("storeService")
	private StoreService storeService;
	
	@Autowired
	@Qualifier("saleService")
	private SaleService saleService;
	
	@Autowired
	@Qualifier("saleRequestService")
	private SaleRequestService saleRequestService;
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	@Autowired
	@Qualifier("storeRepository")
	private IStoreRepository storeRepository;
	
	@Autowired
	@Qualifier("storeConverter")
	private StoreConverter storeConverter;
	
	
	@Override
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}
	
	@Override
	public EmployeeModel insertOrUpdate(EmployeeModel employeeModel) {
		//employeeModel.setStore(StoreService.findById(employeeModel.getStore().getId()));
		Employee employee = employeeRepository.save(employeeConverter.modelToEntity(employeeModel));
		return employeeConverter.entityToModel(employee);
	}
	
	@Override
	public EmployeeModel insert(EmployeeModel employeeModel) {
		
		//Relaciono el id del store con todo el objeto store y lo seteo en employeeModel.
		Store store = storeRepository.findById(employeeModel.getStore().getId());
		StoreModel storeModel = storeConverter.entityToModel(store);
		employeeModel.setStore(storeModel);
		
		//Se guarda el employee en la bd.
		Employee employee = employeeConverter.modelToEntity(employeeModel);
		employeeRepository.save(employee);
		
		//Seteo en el Set<Employee> del store correspondiente. 
		employee.getStore().getSetEmployees().add(employee);
		storeService.update(storeConverter.entityToModel(employee.getStore()));
		
		return employeeConverter.entityToModel(employee);
	}
	
	@Override
	public EmployeeModel update(EmployeeModel employeeModel) {
		employeeModel.setStore(storeService.findById(employeeModel.getStore().getId()));
		Employee employee = employeeRepository.save(employeeConverter.modelToEntity(employeeModel));
		return employeeConverter.entityToModel(employee);

	}
	
	@Override
	public boolean remove(int id) {
		try {
			employeeRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public void calculatePay(Employee e, int idSale ) {
		SaleModel s = saleService.findById(idSale);
		e.setMinimunWage(20000);
		e.setPlus(0); //solo si se trabaja sin bd.
		if(s.getEmployeeInCharge().getId() == e.getId()) {
		for(SaleRequestModel sq: s.getSetSaleRequests()) {
			SaleRequestModel sqp = saleRequestService.findById(sq.getId());
			float precio = productService.findById(sqp.getProduct().getId()).getUnitPrice();
			e.setPlus((float) (e.getPlus() +((precio*0.05)) * sqp.getAmount()));
		}
		}
		for(SaleRequestModel sq: s.getSetSaleRequests()) {
			if(sq.getAssistantEmployee() != null) {
				Employee emp = employeeRepository.findById(sq.getAssistantEmployee().getId());
				SaleRequestModel sqp = saleRequestService.findById(sq.getId());
				float precio = productService.findById(sqp.getProduct().getId()).getUnitPrice();
				emp.setPlus((float) (emp.getPlus() + ((precio*0.02) * sqp.getAmount())));
				e.setPlus((float) (e.getPlus() + ((precio*0.03) * sqp.getAmount())));
				//update(employeeConverter.entityToModel(emp));
			}
		}
		//update(employeeConverter.entityToModel(e));
	}
	
	public Set<Employee> setSalarys(String date){
		Set<Employee> employees = new HashSet<Employee>();
		for (Sale s: saleService.getAll()) {
			String dateSale = dateSale(s);
			if(dateSale.equals(date)) {
				Employee employee = employeeRepository.findById(s.getEmployeeInCharge().getId());
				calculatePay(employee, s.getId());
				employees.add(employee);
			}
		}
		return employees;
	}
	
	private String dateSale(Sale s) {
		String dateSale = "";
		if(s.getDate().getMonthValue() < 10) {
			dateSale = String.valueOf(s.getDate().getYear()) +"-0"+String.valueOf(s.getDate().getMonthValue());
		}
		else {
			dateSale = String.valueOf(s.getDate().getYear()) +"-"+String.valueOf(s.getDate().getMonthValue());
		}
		return dateSale;
	}
	
	@Override
	public EmployeeModel findById(int id) {
		return employeeConverter.entityToModel(employeeRepository.findById(id));
	}
	
	public List<Employee> getEmployeeByStore(int idStore){
		List<Employee> employeesStore = new ArrayList<Employee>();
		for (Employee e : getAll()) {
			if(e.getStore().getId() == idStore) {
				employeesStore.add(e);
			}
		}
		return employeesStore;
	}
	
}