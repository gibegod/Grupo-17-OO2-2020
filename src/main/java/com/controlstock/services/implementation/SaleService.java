package com.controlstock.services.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.controlstock.converters.SaleConverter;
import com.controlstock.converters.SaleRequestConverter;
import com.controlstock.converters.StoreConverter;
import com.controlstock.converters.AddressConverter;
import com.controlstock.converters.ClientConverter;
import com.controlstock.converters.EmployeeConverter;
import com.controlstock.converters.ProductConverter;
import com.controlstock.converters.ProductRankingConverter;
import com.controlstock.entities.Sale;
import com.controlstock.entities.SaleRequest;
import com.controlstock.entities.Store;
import com.controlstock.helpers.DateBatchComparator;
import com.controlstock.entities.Employee;
import com.controlstock.entities.Product;
import com.controlstock.entities.ProductRanking;
import com.controlstock.entities.Address;
import com.controlstock.entities.Client;
import com.controlstock.models.SaleModel;
import com.controlstock.models.SaleRequestModel;
import com.controlstock.models.StoreModel;
import com.controlstock.models.EmployeeModel;
import com.controlstock.models.ProductModel;
import com.controlstock.models.ProductRankingModel;
import com.controlstock.models.AddressModel;
import com.controlstock.models.BatchModel;
import com.controlstock.models.ClientModel;
import com.controlstock.repositories.ISaleRepository;
import com.controlstock.repositories.ISaleRequestRepository;
import com.controlstock.repositories.IStoreRepository;
import com.controlstock.repositories.IAddressRepository;
import com.controlstock.repositories.IClientRepository;
import com.controlstock.repositories.IEmployeeRepository;
import com.controlstock.services.IProductRankingService;
import com.controlstock.services.IProductService;
import com.controlstock.services.ISaleRequestService;
import com.controlstock.services.ISaleService;
import com.controlstock.services.IStoreService;

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

	@Autowired
	@Qualifier("storeRepository")
	private IStoreRepository storeRepository;

	@Autowired
	@Qualifier("storeConverter")
	private StoreConverter storeConverter;

	@Autowired
	@Qualifier("storeService")
	private IStoreService storeService;

	@Autowired
	@Qualifier("addressRepository")
	private IAddressRepository addressRepository;

	@Autowired
	@Qualifier("addressConverter")
	private AddressConverter addressConverter;

	@Autowired
	@Qualifier("batchService")
	private BatchService batchService;

	@Autowired
	@Qualifier("productRankingService")
	private IProductRankingService productRankingService;

	@Autowired
	@Qualifier("productConverter")
	private ProductConverter productConverter;
	
	@Autowired
	@Qualifier("saleRequestService")
	private ISaleRequestService saleRequestService;
	

	@Override
	public List<Sale> getAll() {
		return saleRepository.findAll();
	}

	@Override
	public SaleModel insert(SaleModel saleModel) {

		// Si employee no es null
		if (saleModel.getEmployeeInCharge() != null) {
			Employee employee = employeeRepository.findById(saleModel.getEmployeeInCharge().getId());
			EmployeeModel employeeModel = employeeConverter.entityToModel(employee);
			saleModel.setEmployeeInCharge(employeeModel);
		}

		Store store = storeRepository.findById(saleModel.getEmployeeInCharge().getStore().getId());
		StoreModel storeModel = storeConverter.entityToModel(store);
		saleModel.setStoreModel(storeModel);

		Address address = addressRepository.findById(saleModel.getStoreModel().getAddress().getId());
		AddressModel addressModel = addressConverter.entityToModel(address);
		saleModel.getStoreModel().setAddress(addressModel);

		// Si cliente no es null.
		if (saleModel.getClient() != null) {
			Client client = clientRepository.findById(saleModel.getClient().getId());
			ClientModel clientModel = clientConverter.entityToModel(client);
			saleModel.setClient(clientModel);
		}

		Sale sale = saleRepository.save(saleConverter.modelToEntity(saleModel));
		return saleConverter.entityToModel(sale);
	}

	@Override
	public SaleModel update(SaleModel saleModel) {

		// System.out.println(saleModel.getId()); //Bien

		Sale sale = saleRepository.findById(saleModel.getId());
		SaleModel saleModelDB = saleConverter.entityToModel(sale); // El sale que esta en la base de datos

		// System.out.println(saleModelDB.getEmployeeInCharge().getName()); ///Bien

		Employee employee = employeeRepository.findById(saleModelDB.getEmployeeInCharge().getId());
		EmployeeModel employeeModel = employeeConverter.entityToModel(employee);
		saleModel.setEmployeeInCharge(employeeModel);

		Store store = storeRepository.findById(saleModelDB.getEmployeeInCharge().getStore().getId());
		StoreModel storeModel = storeConverter.entityToModel(store);
		saleModel.setStoreModel(storeModel);

		Address address = addressRepository.findById(saleModelDB.getStoreModel().getAddress().getId());
		AddressModel addressModel = addressConverter.entityToModel(address);
		saleModel.getStoreModel().setAddress(addressModel);

		if (saleModel.getClient() != null) {
			saleModel.setClient(clientService.findById(saleModel.getClient().getId()));
			sale.setClient(clientConverter.modelToEntity(clientService.findById(saleModel.getClient().getId())));
			sale.setDate(saleModel.getDate());
			saleRepository.saveAndFlush(sale);

			//ProductRanking
			for (SaleRequest sr : sale.getSetSaleRequests()) {
				
				int aux = 0;
				int idAux = 0;
				if(productRankingService.getAll().isEmpty() == false) {
					for(ProductRanking pr : productRankingService.getAll()) {
						if(pr.getProduct().getId() == sr.getProduct().getId()) {
							aux = 1;
							idAux = pr.getId();
						}
					}
				}
				
				//Si no hay ningun productoRanking con el id del producto del saleRequest va al insert,si no al update.
				if (aux == 0) {
					productRankingService.insert(new ProductRankingModel(0, 
							productConverter.entityToModel(sr.getProduct()), sr.getAmount()));
				} else {
					productRankingService.update(new ProductRankingModel(idAux,
							productConverter.entityToModel(sr.getProduct()), sr.getAmount()));
				}
			}
			
			subtractStock(sale);
			employeeService.calculatePay(sale.getEmployeeInCharge(), sale.getId());
		}

		return saleModel;
	}

	// Si el total de la venta es 0 o el status es false (venta no terminada), elimina la venta y los 
	//saleRequest correspondientes.
	@Override
	public void checkSales(List<Sale> salesList) {

		for (Sale s : salesList) {

			Sale sale = saleRepository.findById(s.getId());

			if (sale.getTotalPrice() == 0.0 || sale.getStatus() == false) {
				for(SaleRequest sr : sale.getSetSaleRequests()) {
					saleRequestService.remove(sr.getId());
				}
				remove(sale.getId());
			}
		}
	}

	/*--------------------------------------------------*/

	// Cantidad en SR hay que restarla al lote correspondiente al store y al
	// producto.
	void subtractStock(Sale sale) {
		/*
		 * SaleModel saleModel = saleConverter.entityToModel(sale);
		 * Set<SaleRequestModel> setSaleRequestsModel = saleModel.getSetSaleRequests();
		 * Set<BatchModel> setBatchModel = saleModel.getStoreModel().getSetBatchs();
		 * List<BatchModel> aux = new ArrayList<>(setBatchModel);
		 * 
		 * // current amount = current amount - amount for(SaleRequestModel srm :
		 * setSaleRequestsModel) { //por cada pedido del set
		 * 
		 * ProductModel productSR = srm.getProduct(); //saco el producto y la cantidad
		 * inicial. int amountSR = srm.getAmount();
		 * 
		 * Collections.sort(aux, new DateBatchComparator()); //magia ordena batch
		 * //clase que hereda del comparator, esta en helpers. Necesita que sea una
		 * lista para funcionar, por eso la transformacion.
		 * 
		 * for(BatchModel bm : aux) { //por cada lote en el store. ProductModel productB
		 * = bm.getProduct(); int amountB = bm.getCurrentAmount();
		 * 
		 * if (productSR.getId() == productB.getId()) { //si el producto es igual.
		 * amountB = amountB - amountSR; bm.setCurrentAmount(amountB);
		 * batchService.updateCurrentAmount(bm); //aux.remove(bm); //saca el lote de la
		 * lista. } }
		 * 
		 * }
		 */

		SaleModel saleModel = saleConverter.entityToModel(sale);
		Set<SaleRequestModel> setSaleRequestsModel = saleModel.getSetSaleRequests();
		Store store = storeRepository.findById(saleModel.getEmployeeInCharge().getStore().getId()); // NO
		for (SaleRequestModel srm : setSaleRequestsModel) {

			if (srm.getAssistantEmployee() != null) { // Si tiene auxEmployee y es SR de otra store.
				// srm.getAssistantEmployee().getStore().getId();
				storeService.substractBatches(srm.getAssistantEmployee().getStore().getId(), srm.getProduct().getId(),
						srm.getAmount());
			} else {
				// System.out.println(srm.getProduct().getId());
				// System.out.println(srm.getAmount());
				storeService.substractBatches(store.getId(), srm.getProduct().getId(), srm.getAmount());
			}
		}

	}

	// Seteamos el status en true
	public void updateStatus(SaleModel saleModel) {
		saleModel = update(saleModel);
		Sale sale = saleRepository.findById(saleModel.getId());
		sale.setStatus(true);
		saleRepository.saveAndFlush(sale);
		saleModel.setStatus(true);
	}

	@Override
	public boolean remove(int id) {
		try {
			saleRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public SaleModel findById(int id) {
		return saleConverter.entityToModel(saleRepository.findById(id));
	}

	// Calcula el total del sale
	public float calculateTotal(int id) {
		Sale sale = saleRepository.findById(id);
		float total = 0;
		for (SaleRequest request : sale.getSetSaleRequests()) {
			total = total + (request.getProduct().getUnitPrice() * request.getAmount());
		}
		sale.setTotalPrice(total);
		saleRepository.saveAndFlush(sale);
		return total;
	}

	// Busca entre todas las sales y devuelve la que es false (que esta en proceso).
	// No deberia ser una lista pero es para que no se rompa en el desarrollo.
	@Override
	public List<Sale> getSaleListByStatus() {
		List<Sale> sales = new ArrayList<Sale>();
		for (Sale sale : getAll()) {
			if (sale.getStatus() == false) {
				sales.add(sale);
			}
		}
		return sales;
	}

	@Override
	public Sale getSaleByStatus() {
		for (Sale sale : getAll()) {
			if (sale.getStatus() == false) {
				return sale;
			}
		}
		return null;
	}

}
