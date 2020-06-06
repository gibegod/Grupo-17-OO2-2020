package com.controlstock.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.controlstock.helpers.ViewRouteHelper;
import com.controlstock.models.EmployeeModel;
import com.controlstock.models.StoreModel;
import com.controlstock.models.StoresModel;
import com.controlstock.services.IAddressService;
import com.controlstock.services.IEmployeeService;
import com.controlstock.services.IStoreService;
import com.controlstock.services.implementation.StoreService;

@Controller
@RequestMapping("/store")
public class StoreController {

	@Autowired
	@Qualifier("storeService")
	private IStoreService storeService;
	
	@Autowired
	@Qualifier("addressService")
	private IAddressService addressService;
	
	@Autowired
	@Qualifier("employeeService")
	private IEmployeeService employeeService;
	
	

	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STORE_INDEX);
		mAV.addObject("stores", storeService.getAll());
		
		/*//TEST distanceStores
		 
		StoreModel store1 = storeService.findById(1);
		StoreModel store2 = storeService.findById(2);
		System.out.println("Distancia: " + distanceStores(store1.getAddress().getLatitude(), 
											store1.getAddress().getLongitude(), store2.getAddress().getLatitude(), 
											store2.getAddress().getLongitude()) + " km.");
		*/
		
		//TEST funcionamiento de Set<Employee>
		
		/*Set<EmployeeModel> aa = storeService.findById(1).getSetEmployees(); //Store de id 1.
		System.out.println("Size set store 1: " + aa.size());
		for(EmployeeModel e : aa) {
			System.out.println("Employee ID: " + e.getId());
		}*/
		
		
		return mAV;
	}

	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STORE_NEW);
		mAV.addObject("store", new StoreModel());
		mAV.addObject("address", addressService.getAll());
		return mAV;
	}

	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("store") StoreModel storeModel) {
		storeService.insert(storeModel);
		return new RedirectView(ViewRouteHelper.STORE_ROOT);
	}

	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STORE_UPDATE);
		mAV.addObject("store", storeService.findById(id));
		mAV.addObject("address", addressService.getAll());
		mAV.addObject("employees", employeeService.getEmployeeByStore(id));
		return mAV;
	}

	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("store") StoreModel storeModel) {
		storeService.update(storeModel);
		return new RedirectView(ViewRouteHelper.STORE_ROOT);
	}

	@PostMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		storeService.remove(id);
		return new RedirectView(ViewRouteHelper.STORE_ROOT);
	}
	
	@GetMapping("/partial/{productid}/{amount}/{saleid}")
	public ModelAndView getPartial(@PathVariable("productid") int productId, @PathVariable("amount") int amount, 
			@PathVariable("saleid") int saleId) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STORE_PARTIAL_VIEW);
		//Stores que contienen el product con la id y la cantidad.
		
		mAV.addObject("stores", storeService.getStoresByStock(productId, amount, saleId));
		return mAV;
	}
	
	@GetMapping("/partial/employees/{id}")
	public ModelAndView getPartialEmployee(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STORE_PARTIAL_VIEW_EMPLOYEES);
		mAV.addObject("employees", storeService.findById(id).getSetEmployees());
		return mAV;
	}
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ModelAndView distanceStores(StoresModel stores) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STORE_INDEX);
		mAV.addObject("stores", storeService.getAll());
		
		float latstore1 = storeService.findById(stores.getStore1().getId()).getAddress().getLatitude();
		float longstore1 = storeService.findById(stores.getStore1().getId()).getAddress().getLongitude();
		float latstore2 = storeService.findById(stores.getStore2().getId()).getAddress().getLatitude();
		float longstore2 = storeService.findById(stores.getStore2().getId()).getAddress().getLongitude();
		
		mAV.addObject("distance" , storeService.distanceStores(latstore1, longstore1, latstore2, longstore2));
		
		return mAV;
	}
	
}
