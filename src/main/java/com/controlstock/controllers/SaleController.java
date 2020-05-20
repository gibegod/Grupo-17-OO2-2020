package com.controlstock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.controlstock.helpers.ViewRouteHelper;
import com.controlstock.models.SaleRequestModel;

import com.controlstock.models.SaleModel;
import com.controlstock.services.ISaleService;
import com.controlstock.services.ISaleRequestService;
import com.controlstock.services.IClientService;
import com.controlstock.services.IEmployeeService;

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
	
	@GetMapping("")
	public ModelAndView index () {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALE_INDEX);
		mAV.addObject("sales", saleService.getAll());
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALE_NEW);
		mAV.addObject("sale", new SaleModel());
		mAV.addObject("clients", clientService.getAll());
		mAV.addObject("employees", employeeService.getAll());
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("sale") SaleModel saleModel) {
		saleService.insert(saleModel);
		return new RedirectView(ViewRouteHelper.SALE_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALE_UPDATE);
		mAV.addObject("sale", saleService.findById(id));
		mAV.addObject("client", clientService.getAll());
		mAV.addObject("employee", employeeService.getAll());
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("sale") SaleModel saleModel) {
		saleService.update(saleModel);
		return new RedirectView(ViewRouteHelper.SALE_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView delete (@PathVariable("id") int id) {
		saleService.remove(id);
		return new RedirectView(ViewRouteHelper.SALE_ROOT);
	}
		
}