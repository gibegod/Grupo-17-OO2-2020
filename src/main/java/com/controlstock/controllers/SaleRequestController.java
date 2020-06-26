package com.controlstock.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.controlstock.converters.SaleConverter;
import com.controlstock.helpers.ViewRouteHelper;
import com.controlstock.models.SaleModel;
import com.controlstock.models.SaleRequestModel;
import com.controlstock.services.ISaleRequestService;
import com.controlstock.services.ISaleService;
import com.controlstock.services.IStoreService;
import com.controlstock.services.IProductService;
import com.controlstock.services.IEmployeeService;

@Controller
@RequestMapping("/sale/saleRequest")
public class SaleRequestController {

	@Autowired
	@Qualifier("saleRequestService")
	private ISaleRequestService saleRequestService;

	@Autowired
	@Qualifier("productService")
	private IProductService productService;

	@Autowired
	@Qualifier("employeeService")
	private IEmployeeService employeeService;

	@Autowired
	@Qualifier("saleService")
	private ISaleService saleService;

	@Autowired
	@Qualifier("storeService")
	private IStoreService storeService;

	@Autowired
	@Qualifier("saleConverter")
	private SaleConverter saleConverter;

	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALEREQUEST_INDEX);
		mAV.addObject("saleRequests", saleRequestService.getAll());
		return mAV;
	}

	// Crea el SaleRequest.
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALEREQUEST_NEW);
		mAV.addObject("saleRequest", new SaleRequestModel());
		mAV.addObject("sales", saleService.getSaleByStatus());
		mAV.addObject("saleRequests", saleService.findById(saleService.getSaleByStatus().getId()).getSetSaleRequests());
		mAV.addObject("products", storeService.getProductsByStore(saleService.getSaleByStatus().getStore().getId()));
		//mAV.addObject("employees", employeeService.getAll());
		mAV.addObject("batchs", storeService.findById(saleService.getSaleByStatus().getStore().getId()).getSetBatchs());
		return mAV;
	}

	// Crea el SaleRequest cuando se requiere un producto de otra sucursal.
	@GetMapping("/new2")
	public ModelAndView create2() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALEREQUEST_NEW2);
		mAV.addObject("saleRequest", new SaleRequestModel());
		mAV.addObject("sales", saleService.getSaleByStatus()); // Tiene que haber 1 solo sale.
		mAV.addObject("products", productService.getAll());
		mAV.addObject("batchs", storeService.findById(saleService.getSaleByStatus().getStore().getId()).getSetBatchs());
		mAV.addObject("stores", storeService.getAll());
		return mAV;
	}

	@PostMapping("/create")
	public ModelAndView create(@Valid @ModelAttribute("saleRequest") SaleRequestModel saleRequestModel,
			BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.SALEREQUEST_NEW);
			mAV.addObject("sales", saleService.getSaleByStatus());
			mAV.addObject("saleRequests", saleService.findById(saleService.getSaleByStatus().getId()).getSetSaleRequests());
			mAV.addObject("products", storeService.getProductsByStore(saleService.getSaleByStatus().getStore().getId()));
			mAV.addObject("batchs", storeService.findById(saleService.getSaleByStatus().getStore().getId()).getSetBatchs());
		} else {
			mAV.setViewName("redirect:/sale/saleRequest/new");
			saleRequestService.insert(saleRequestModel);
		}
		return mAV;
	}
	
	@PostMapping("/create2")
	public ModelAndView create2(@Valid @ModelAttribute("saleRequest") SaleRequestModel saleRequestModel,
			BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.SALEREQUEST_NEW2);
			mAV.addObject("saleRequest", new SaleRequestModel());
			mAV.addObject("sales", saleService.getSaleByStatus()); // Tiene que haber 1 solo sale.
			mAV.addObject("products", productService.getAll());
			mAV.addObject("batchs", storeService.findById(saleService.getSaleByStatus().getStore().getId()).getSetBatchs());
			mAV.addObject("stores", storeService.getAll());
		} else {
			mAV.setViewName("redirect:/sale/saleRequest/new");
			saleRequestService.insert(saleRequestModel);
		}
		return mAV;
	}

	//Creo que no se usa
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALEREQUEST_UPDATE);
		mAV.addObject("saleRequest", saleRequestService.findById(id));
		mAV.addObject("products", productService.getAll());
		mAV.addObject("employee", employeeService.getAll());
		return mAV;
	}

	//Creo que no se usa
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("saleRequest") SaleRequestModel saleRequestModel) {
		saleRequestService.update(saleRequestModel);
		return new RedirectView(ViewRouteHelper.SALEREQUEST_ROOT);
	}

	@PostMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		saleRequestService.remove(id);
		return new RedirectView(ViewRouteHelper.SALEREQUEST_ROOT2);
	}

	@GetMapping("/stockVerify/{idSale}/{idProduct}/{quantity}")
	public ModelAndView stockVerify(@PathVariable("idSale") int idSale, @PathVariable("idProduct") int idProduct,
			@PathVariable("quantity") int quantity) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALEREQUEST_VERIFY_STOCK);
		SaleModel sale = saleService.findById(idSale);
		if (!storeService.validateStock(sale.getStoreModel().getId(), idProduct, quantity)) {
			mAV.addObject("message", new Message("No hay stock"));
		} else {
			mAV.addObject("message", new Message(""));
		}
		return mAV;
	}
}

//Clase Message
class Message {
	private String text;

	public Message(String text) {
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	};
}
