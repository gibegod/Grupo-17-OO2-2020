package com.controlstock.converters;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.controlstock.entities.Client;
import com.controlstock.entities.Employee;
import com.controlstock.entities.Sale;
import com.controlstock.models.SaleModel;
import com.controlstock.models.StoreModel;

@Component
public class SaleConverter {

	@Autowired
	@Qualifier("clientConverter")
	private ClientConverter clientConverter;
	
	
	@Autowired
	@Qualifier("employeeConverter")
	private EmployeeConverter employeeConverter;
	
	public SaleModel entityToModel(Sale sale) {
		return new SaleModel(sale.getId(),employeeConverter.entityToModel(sale.getEmployeeInCharge()), clientConverter.entityToModel(sale.getClient()), sale.getDate(),
				sale.getTime());
	}
	
	public Sale modelToEntity(SaleModel sale) {
		return new Sale(sale.getId(),employeeConverter.modelToEntity(sale.getEmployeeInCharge()), clientConverter.modelToEntity(sale.getClient()), sale.getDate(),
				sale.getTime());
	}
	

}
