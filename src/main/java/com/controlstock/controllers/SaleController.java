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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.controlstock.helpers.ViewRouteHelper;
import com.controlstock.models.SaleRequestModel;
import com.controlstock.models.StoreModel;
import com.controlstock.models.SaleModel;
import com.controlstock.services.ISaleService;
import com.controlstock.services.IStoreService;
import com.controlstock.services.ISaleRequestService;
import com.controlstock.services.IClientService;
import com.controlstock.services.IEmployeeService;
import com.controlstock.services.IProductService;

@Controller
@RequestMapping("/sale")
public class SaleController {

	@Autowired
	@Qualifier("saleService")
	private ISaleService saleService;
	
	@Autowired
	@Qualifier("clientService")
	private IClientService clientService;
	
	@Autowired
	@Qualifier("employeeService")
	private IEmployeeService employeeService;
	
	@Autowired
	@Qualifier("storeService")
	private IStoreService storeService;
	
	@Autowired
	@Qualifier("saleRequestService")
	private ISaleRequestService saleRequestService;
	
	@Autowired
	@Qualifier("productService")
	private IProductService productService;
	
	@GetMapping("")
	public ModelAndView index () {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALE_INDEX);
		mAV.addObject("sales", saleService.getAll());
		
		
		//TEST funcionamiento de Set<SaleRequest>
		/*
		Set<SaleRequestModel> aa = saleService.findById(1).getSetSaleRequests(); //Store de id 1.
		System.out.println("Size set store 1: " + aa.size());
		for(SaleRequestModel s : aa) {
			System.out.println("SaleRequest ID: " + s.getId());
		}*/
		
		return mAV;
	}
	
	//Selecciona Store
	@GetMapping("/initial")
	public ModelAndView initial() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALE_INITIAL);
		mAV.addObject("sale", new SaleModel());
		mAV.addObject("stores", storeService.getAll());
		return mAV;
	}
	
	//Selecciona empleado del store
	@RequestMapping(value = "/selectEmployee", method = RequestMethod.GET)
	public ModelAndView selectEmployee(@ModelAttribute("sale") SaleModel saleModel) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALE_SELECTEMPLOYEE);
		mAV.addObject("employees", employeeService.getEmployeeByStore(saleModel.getStoreModel().getId()));
		return mAV;
	}
	
	//Guarda el sale y va a crear un saleRequest.
	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("sale") SaleModel saleModel) {
		saleService.insert(saleModel);
		return new RedirectView(ViewRouteHelper.SALEREQUEST_NEW);
	}
	
	//No se usa
	@RequestMapping(value = "/addSaleRequest", method = RequestMethod.GET)
	public ModelAndView addSaleRequest(@ModelAttribute("sale") SaleModel saleModel) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALE_ADDSALEREQUEST);
		mAV.addObject("saleRequestModel", new SaleRequestModel());
		mAV.addObject("saleRequests", saleModel.getSetSaleRequests());
		mAV.addObject("products", storeService.getProductsByStore(saleModel.getStoreModel().getId()));
		mAV.addObject("employees", employeeService.getAll());
		return mAV;
	}
	
	//No se usa
	@PostMapping("/createSaleRequest")
	public RedirectView createSR(@ModelAttribute("sale") SaleModel saleModel) {
		saleService.insert(saleModel);
		return new RedirectView(ViewRouteHelper.SALE_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		//ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALE_UPDATE);
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALE_FINAL);
		mAV.addObject("sale", saleService.findById(id));
		mAV.addObject("status", true);
		mAV.addObject("clients", clientService.getAll());
		mAV.addObject("employee", saleService.findById(id).getEmployeeInCharge());
		mAV.addObject("total", saleService.calculateTotal(id));
		mAV.addObject("saleRequests", saleService.findById(saleService.getSaleByStatus().getId()).getSetSaleRequests());
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("sale") SaleModel saleModel) {
		saleService.updateStatus(saleModel);
		return new RedirectView(ViewRouteHelper.SALE_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView delete (@PathVariable("id") int id) {
		saleService.remove(id);
		return new RedirectView(ViewRouteHelper.SALE_ROOT);
	}
		
}