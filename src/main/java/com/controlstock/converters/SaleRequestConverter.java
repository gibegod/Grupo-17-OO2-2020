package com.controlstock.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.controlstock.entities.SaleRequest;
import com.controlstock.models.SaleRequestModel;

@Component
public class SaleRequestConverter {
	
	@Autowired
	@Qualifier("productConverter")
	private ProductConverter productConverter;
	
	@Autowired
	@Qualifier("employeeConverter")
	private EmployeeConverter employeeConverter;
	
	public SaleRequestModel entityToModel(SaleRequest saleRequest) {
		if(saleRequest.getAssistantEmployee()!=null) {
		return new SaleRequestModel (saleRequest.getId(), productConverter.entityToModel(saleRequest.getProduct()), saleRequest.getAmount(), employeeConverter.entityToModel(saleRequest.getAssistantEmployee()));}
		else {
			return new SaleRequestModel (saleRequest.getId(), productConverter.entityToModel(saleRequest.getProduct()), saleRequest.getAmount());
		}
	}
	
	public SaleRequest modelToEntity(SaleRequestModel saleRequest) {
		if (saleRequest.getAssistantEmployee()!=null) {
		return new SaleRequest (saleRequest.getId(), productConverter.modelToEntity(saleRequest.getProduct()), saleRequest.getAmount(), employeeConverter.modelToEntity(saleRequest.getAssistantEmployee()));}
		else {
		return new SaleRequest (saleRequest.getId(), productConverter.modelToEntity(saleRequest.getProduct()), saleRequest.getAmount());}
	}

}
