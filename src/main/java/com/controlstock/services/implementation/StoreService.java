package com.controlstock.services.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.AddressConverter;
import com.controlstock.converters.EmployeeConverter;
import com.controlstock.converters.StoreConverter;
import com.controlstock.entities.Address;
import com.controlstock.entities.Batch;
import com.controlstock.entities.Client;
import com.controlstock.entities.Product;
import com.controlstock.entities.Sale;
import com.controlstock.entities.Store;
import com.controlstock.models.AddressModel;
import com.controlstock.models.StoreModel;
import com.controlstock.repositories.IAddressRepository;
import com.controlstock.repositories.IProductRepository;
import com.controlstock.repositories.ISaleRepository;
import com.controlstock.repositories.IEmployeeRepository;
import com.controlstock.repositories.IBatchRepository;
import com.controlstock.repositories.IStoreRepository;
import com.controlstock.services.ISaleService;
import com.controlstock.services.IStoreService;

@Service("storeService")
public class StoreService implements IStoreService {

	@Autowired
	@Qualifier("storeRepository")
	private IStoreRepository storeRepository;

	@Autowired
	@Qualifier("storeConverter")
	private StoreConverter storeConverter;

	@Autowired
	@Qualifier("addressService")
	private AddressService addressService;

	@Autowired
	@Qualifier("batchService")
	private BatchService batchService;

	@Autowired
	@Qualifier("addressRepository")
	private IAddressRepository addressRepository;

	@Autowired
	@Qualifier("addressConverter")
	private AddressConverter addressConverter;

	@Autowired
	@Qualifier("employeeService")
	private EmployeeService employeeService;

	@Autowired
	@Qualifier("batchRepository")
	private IBatchRepository batchRepository;

	@Autowired
	@Qualifier("employeeRepository")
	private IEmployeeRepository employeeRepository;

	@Autowired
	@Qualifier("productRepository")
	private IProductRepository productRepository;

	@Autowired
	@Qualifier("employeeConverter")
	private EmployeeConverter employeeConverter;

	@Autowired
	@Qualifier("saleService")
	private ISaleService saleService;
	
	@Autowired
	@Qualifier("saleRepository")
	private ISaleRepository saleRepository;
	
	
	@Override
	public List<Store> getAll() {
		return storeRepository.findAll();
	}

	@Override
	public StoreModel insertOrUpdate(StoreModel storeModel) {
		Store store = storeRepository.save(storeConverter.modelToEntity(storeModel));
		return storeConverter.entityToModel(store);
	}

	@Override
	public List<Product> getProductsByStore(int id) {
		Store store = storeRepository.findById(id);
		List<Product> products = new ArrayList<Product>();
		for (Batch b : store.getSetBatchs()) {
			products.add(b.getProduct());
		}
		
		products.sort(Comparator.comparing(Product::getId));
		return products;
	}
	
	@Override
	public List<Sale> getSalesInRangeByStore(int id, LocalDateTime minDate, LocalDateTime maxDate) {
		Store store = storeRepository.findById(id);
		List<Sale> sales = new ArrayList<Sale>();
		for (Sale s : saleService.getAll()) {
				if(s.getDate().isAfter(minDate) && s.getDate().isBefore(maxDate) && s.getStore().getId()==store.getId()) {
					
				sales.add(s);
				
			}
		}
		return sales;
	}

	public List<Store> getStoresByStock(int productId, int amount, int saleId) {
		List<Store> storesProduct = getStoresByProductId(productId);
		List<Store> stores = new ArrayList<Store>();
		List<Store> storesList = new ArrayList<Store>();
		Store storeActual = saleRepository.findById(saleId).getEmployeeInCharge().getStore();
		
		for (Store store : storesProduct) {
			//Si el store tiene stock del producto y si el store no es el actual..
			if (amount <= getProductQuantity(store.getId(), productId) && store.getId() != storeActual.getId()) {
				stores.add(store);
			}
		}
		
		//Genero un mapa que funciona como diccionario, con el tipo "llave, valor", donde la llave es la distancia.
		Map<Float, String> listaDistancias = new HashMap<Float, String>();
		//Creo la lista de distancias, para despues ir comparando.
		HashSet<Float> distancias = new HashSet<Float>();
		
		for (Store s : stores) {
			//lat1, long1, lat2, long2
			float distance = distanceStores(storeActual.getAddress().getLatitude(), storeActual.getAddress().getLongitude(),
					s.getAddress().getLatitude(), s.getAddress().getLongitude());
			//Ingreso en el diccionario como llave la distancia y como valor el id. 
			listaDistancias.put(distance, String.valueOf(s.getId()));
			distancias.add(distance); //Voy guardando todas las distancias.
		}
		
		List<Float> distanciasList = new ArrayList<>(distancias);
		Collections.sort(distanciasList); //Ordeno las distancias.
		for(Float d: distancias) { //Itero las distancias y agrego en storesList.
			storesList.add(storeRepository.findById(Integer.parseInt(listaDistancias.get(d))));
		}
		//tiene que retornar storesList
		return storesList;

	}

	@Override
	public int getProductQuantity(int idStore, int idProduct) {

		Product product = productRepository.findById(idProduct);

		int cantidad = 0;
		for (int indice = 0; indice < getActiveBatchs(idStore, idProduct).size(); indice++) {
			if (this.getActiveBatchs(idStore, idProduct).get(indice).getProduct().getId() == product.getId()) {
				cantidad += this.getActiveBatchs(idStore, idProduct).get(indice).getCurrentAmount();
			}
		}
		return cantidad;
	}

	@Override
	public List<Batch> getActiveBatchs(int idStore, int idProduct) {
		Store store = storeRepository.findById(idStore);
		Product product = productRepository.findById(idProduct);
		List<Batch> batchs = new ArrayList<Batch>();
		for (Batch b : store.getSetBatchs()) {
			if (b.getCurrentAmount() > 0 && b.getProduct().getId() == product.getId()) {
				batchs.add(b);
			}
		}
		return batchs;
	}

	@Override
	public boolean validateStock(int idStore, int idProduct, int quantity) {
		return (quantity <= this.getProductQuantity(idStore, idProduct));
	}

	@Override
	public void substractBatches(int idStore, int idProduct, int quantity) {

		int i = 0;
		List<Batch> active = this.getActiveBatchs(idStore, idProduct);

		while (quantity > 0) {
			if (active.get(i).getCurrentAmount() >= quantity) {
				active.get(i).setCurrentAmount(active.get(i).getCurrentAmount() - quantity);
				quantity = 0;
			} else {
				quantity -= active.get(i).getCurrentAmount();
				active.get(i).setCurrentAmount(0);
			}
			batchRepository.save(active.get(i));
			i++;
		}
	}

	@Override
	public StoreModel insert(StoreModel storeModel) {

		// Relaciono el id de address con todo el objeto address y lo seteo en
		// storeModel.
		Address address = addressRepository.findById(storeModel.getAddress().getId());
		AddressModel addressModel = addressConverter.entityToModel(address);
		storeModel.setAddress(addressModel);

		Store store = storeRepository.save(storeConverter.modelToEntity(storeModel));
		return storeConverter.entityToModel(store);
	}

	@Override
	public StoreModel update(StoreModel storeModel) {

		Address address = addressRepository.findById(storeModel.getAddress().getId());
		AddressModel addressModel = addressConverter.entityToModel(address);
		storeModel.setAddress(addressModel);

		storeModel.setAddress(addressService.findById(storeModel.getAddress().getId()));
		Store store = storeRepository.save(storeConverter.modelToEntity(storeModel));
		return storeConverter.entityToModel(store);
	}

	@Override
	public boolean remove(int id) {
		try {
			storeRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public StoreModel findById(int id) {
		return storeConverter.entityToModel(storeRepository.findById(id));
	}

	@Override
	public List<Store> getStoresByProductId(int id) {
		// Product product = productRepository.findById(id);
		List<Store> stores = new ArrayList<Store>();

		// Recorro todas las stores que hay en el service. Si esta store contiene el
		// producto se guarda en stores.
		for (Store s : getAll()) {
			for (Batch b : s.getSetBatchs()) {
				if (b.getProduct().getId() == id) {
					stores.add(s);
				}
			}
		}
		return stores;
	}

	// Calculo distancia entre Stores
	public float distanceStores(float lat1, float lng1, float lat2, float lng2) {
		float radioTierra = 6371; // en km.
		float dLat = (float) Math.toRadians(lat2 - lat1);
		float dLng = (float) Math.toRadians(lng2 - lng1);
		float sindLat = (float) Math.sin(dLat / 2);
		float sindLng = (float) Math.sin(dLng / 2);
		float va1 = (float) (Math.pow(sindLat, 2)
				+ Math.pow(sindLng, 2) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)));
		float va2 = (float) (2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1)));

		return radioTierra * va2;
	}

}
