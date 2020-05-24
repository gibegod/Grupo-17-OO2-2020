package com.controlstock.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.controlstock.entities.Batch;
import com.controlstock.models.BatchModel;

/* Descripcion: Pasa de model a entity y de entity a model. */

@Component("batchConverter")
public class BatchConverter {
	
	@Autowired
	@Qualifier("productConverter")
	private ProductConverter productConverter;
	
	@Autowired
	@Qualifier("storeConverter")
	private StoreConverter storeConverter;
	
		public BatchModel entityToModel(Batch batch) {
			return new BatchModel (batch.getId(), productConverter.entityToModel(batch.getProduct()), batch.getSize(),
								batch.getCurrentAmount(), batch.getInitialAmount(), batch.getAdmissionDate(), 
								storeConverter.entityToModel(batch.getStore()));
		}
		
	
	public Batch modelToEntity(BatchModel batchModel) {
			return new Batch (batchModel.getId(), productConverter.modelToEntity(batchModel.getProduct()), 
						batchModel.getSize(), batchModel.getCurrentAmount(), batchModel.getInitialAmount(), 
						batchModel.getAdmissionDate(),storeConverter.modelToEntity(batchModel.getStore())
						);
		}
	
	
	//Sin store
	public BatchModel entityToModelSetBatch(Batch batch) {
		return new BatchModel (batch.getId(), productConverter.entityToModel(batch.getProduct()), batch.getSize(),
							batch.getCurrentAmount(), batch.getInitialAmount(), batch.getAdmissionDate());
	}
	
	//Sin Store
	public Batch modelToEntitySetBatch(BatchModel batchModel) {
		return new Batch (batchModel.getId(), productConverter.modelToEntity(batchModel.getProduct()), 
					batchModel.getSize(), batchModel.getCurrentAmount(), batchModel.getInitialAmount(), 
					batchModel.getAdmissionDate());
	}
	
}
