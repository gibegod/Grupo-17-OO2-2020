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

	@Autowired
	@Qualifier("saleConverter")
	private SaleConverter saleConverter;

	public SaleRequestModel entityToModel(SaleRequest saleRequest) {
		if (saleRequest.getAssistantEmployee() != null) {
			return new SaleRequestModel (saleRequest.getId(), saleConverter.entityToModel(saleRequest.getSale()),
					productConverter.entityToModel(saleRequest.getProduct()), saleRequest.getAmount(),
					employeeConverter.entityToModel(saleRequest.getAssistantEmployee()));
		} else {
			return new SaleRequestModel (saleRequest.getId(), saleConverter.entityToModel(saleRequest.getSale()),
					productConverter.entityToModel(saleRequest.getProduct()), saleRequest.getAmount());
		}
	}

	public SaleRequest modelToEntity(SaleRequestModel saleRequestModel) {
		if (saleRequestModel.getAssistantEmployee() != null) {
			return new SaleRequest (saleRequestModel.getId(), saleConverter.modelToEntity(saleRequestModel.getSale()),
					productConverter.modelToEntity(saleRequestModel.getProduct()), saleRequestModel.getAmount(), 
					employeeConverter.modelToEntity(saleRequestModel.getAssistantEmployee()));
		} else {
			return new SaleRequest (saleRequestModel.getId(), saleConverter.modelToEntity(saleRequestModel.getSale()),
					productConverter.modelToEntity(saleRequestModel.getProduct()), saleRequestModel.getAmount());
		}
	}

	//Sin sale
	public SaleRequestModel entityToModelA(SaleRequest saleRequest) {
		if (saleRequest.getAssistantEmployee() != null) {
			return new SaleRequestModel (saleRequest.getId(), productConverter.entityToModel(saleRequest.getProduct()), saleRequest.getAmount(),
					employeeConverter.entityToModel(saleRequest.getAssistantEmployee()));
		} else {
			return new SaleRequestModel (saleRequest.getId(), productConverter.entityToModel(saleRequest.getProduct()),
					saleRequest.getAmount());
		}
	}
	
	//Sin sale
	public SaleRequest modelToEntityA(SaleRequestModel saleRequestModel) {
		if (saleRequestModel.getAssistantEmployee() != null) {
			return new SaleRequest (saleRequestModel.getId(), 
					productConverter.modelToEntity(saleRequestModel.getProduct()), saleRequestModel.getAmount(), 
					employeeConverter.modelToEntity(saleRequestModel.getAssistantEmployee()));
		} else {
			return new SaleRequest (saleRequestModel.getId(),
					productConverter.modelToEntity(saleRequestModel.getProduct()), saleRequestModel.getAmount());
		}
	}
	
}
