package com.controlstock.converters;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.controlstock.entities.Sale;
import com.controlstock.entities.SaleRequest;
import com.controlstock.models.SaleModel;
import com.controlstock.models.SaleRequestModel;

@Component
public class SaleConverter {

	@Autowired
	@Qualifier("clientConverter")
	private ClientConverter clientConverter;

	@Autowired
	@Qualifier("employeeConverter")
	private EmployeeConverter employeeConverter;
	
	@Autowired
	@Qualifier("storeConverter")
	private StoreConverter storeConverter;

	@Autowired
	@Qualifier("saleRequestConverter")
	private SaleRequestConverter saleRequestConverter;

	public SaleModel entityToModel(Sale sale) {
		return new SaleModel(sale.getId(), entityToModelSetSaleRequests(sale.getSetSaleRequests()),
				employeeConverter.entityToModel(sale.getEmployeeInCharge()),
				clientConverter.entityToModel(sale.getClient()), sale.getDate(), storeConverter.entityToModel(sale.getStore()));
	}

	public Sale modelToEntity(SaleModel saleModel) {
		return new Sale(saleModel.getId(), modelToEntitySetSaleRequests(saleModel.getSetSaleRequests()),
				employeeConverter.modelToEntity(saleModel.getEmployeeInCharge()),
				clientConverter.modelToEntity(saleModel.getClient()), saleModel.getDate(), storeConverter.modelToEntity(saleModel.getStoreModel()));
	}

	public Set<SaleRequestModel> entityToModelSetSaleRequests(Set<SaleRequest> setSaleRequests) {
		Set<SaleRequestModel> setSaleRequestsModel = new HashSet<SaleRequestModel>();

		for (SaleRequest s : setSaleRequests) {
			SaleRequestModel saleRequestM = saleRequestConverter.entityToModelA(s);
			setSaleRequestsModel.add(saleRequestM);
		}

		return setSaleRequestsModel;
	}

	public Set<SaleRequest> modelToEntitySetSaleRequests(Set<SaleRequestModel> setSaleRequestsModel) {
		Set<SaleRequest> setSaleRequests = new HashSet<SaleRequest>();

		if (setSaleRequestsModel == null) {
			return setSaleRequests;
		} else {
			for (SaleRequestModel s : setSaleRequestsModel) {
				SaleRequest saleRequest = saleRequestConverter.modelToEntityA(s);
				setSaleRequests.add(saleRequest);
			}
		}

		return setSaleRequests;
	}

}
