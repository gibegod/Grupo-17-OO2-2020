package com.controlstock.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.BatchConverter;
import com.controlstock.converters.ProductConverter;
import com.controlstock.converters.StoreConverter;
import com.controlstock.entities.Batch;
import com.controlstock.entities.Employee;
import com.controlstock.entities.Product;
import com.controlstock.entities.Store;
import com.controlstock.models.BatchModel;
import com.controlstock.models.ProductModel;
import com.controlstock.models.StoreModel;
import com.controlstock.repositories.IBatchRepository;
import com.controlstock.repositories.IProductRepository;
import com.controlstock.repositories.IStoreRepository;
import com.controlstock.services.IBatchService;

@Service("batchService")
public class BatchService implements IBatchService {
	
	@Autowired
	@Qualifier("batchRepository")
	private IBatchRepository batchRepository;
	
	@Autowired
	@Qualifier("batchConverter")
	private BatchConverter batchConverter;
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	@Autowired
	@Qualifier("storeService")
	private StoreService storeService;
	
	@Autowired
	@Qualifier("storeRepository")
	private IStoreRepository storeRepository;
	
	@Autowired
	@Qualifier("storeConverter")
	private StoreConverter storeConverter;
	
	
	@Autowired
	@Qualifier("productRepository")
	private IProductRepository productRepository;
	
	@Autowired
	@Qualifier("productConverter")
	private ProductConverter productConverter;
	
	@Override
	public List<Batch> getAll() {
		return batchRepository.findAll();
	}
	
	
	@Override
	public BatchModel insert(BatchModel batchModel) {
		
		//Relaciono el id del store con todo el objeto store y lo seteo en batchModel.
		Store store = storeRepository.findById(batchModel.getStore().getId());
		StoreModel storeModel = storeConverter.entityToModel(store);
		batchModel.setStore(storeModel);
		
		Product product = productRepository.findById(batchModel.getProduct().getId());
		ProductModel productModel = productConverter.entityToModel(product);
		batchModel.setProduct(productModel);
		
		Batch batch = batchRepository.save(batchConverter.modelToEntity(batchModel));
		
		batch.getStore().getSetBatchs().add(batch);
		storeService.update(storeConverter.entityToModel(batch.getStore()));
		
		return batchConverter.entityToModel(batch);
	}
	
	@Override
	public BatchModel update(BatchModel batchModel) {
		batchModel.setProduct(productService.findById(batchModel.getProduct().getId()));
		batchModel.setStore(storeService.findById(batchModel.getStore().getId()));
		Batch batch = batchRepository.save(batchConverter.modelToEntity(batchModel));
		return batchConverter.entityToModel(batch);
	}
	
	
	@Override
	public BatchModel updateCurrentAmount(BatchModel batchModel) {
		Batch batch2 = batchRepository.findById(batchModel.getId());
		batch2.setCurrentAmount(batchModel.getCurrentAmount());
		Batch batch = batchRepository.save(batch2);
		return batchConverter.entityToModel(batch);
	}
	
	@Override
	public boolean remove(int id) {
		try {
			batchRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public BatchModel findById(int id) {
		return batchConverter.entityToModel(batchRepository.findById(id));
	}
	
	public List<Batch> getBatchByStore(int idStore){
		List<Batch> batchsStore = new ArrayList<Batch>();
		for (Batch b : getAll()) {
			if(b.getStore().getId() == idStore) {
				batchsStore.add(b);
			}
		}
		return batchsStore;
	}
	
}