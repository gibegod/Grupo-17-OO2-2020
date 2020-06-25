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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.controlstock.helpers.ViewRouteHelper;
import com.controlstock.models.AddressModel;
import com.controlstock.services.IAddressService;

@Controller
@RequestMapping("/address")
public class AddressController {

	@Autowired
	@Qualifier("addressService")
	private IAddressService addressService;
	
	@GetMapping("")
	public ModelAndView index () {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ADDRESS_INDEX);
		mAV.addObject("addresses", addressService.getAll());
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ADDRESS_NEW);
		mAV.addObject("address", new AddressModel());
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("address") AddressModel addressModel) {
		addressService.insertOrUpdate(addressModel);
		return new RedirectView(ViewRouteHelper.ADDRESS_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ADDRESS_UPDATE);
		mAV.addObject("address", addressService.findById(id));
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("address") AddressModel addressModel) {
		addressService.insertOrUpdate(addressModel);
		return new RedirectView(ViewRouteHelper.ADDRESS_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView delete (@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
		RedirectView rVT = new RedirectView(ViewRouteHelper.ADDRESS_ROOT);
		boolean rem = addressService.remove(id);	
		
		//Si rem es falso que siga en la misma vista y tire el error. Si es true que vaya al index
		if(rem == false) {
			RedirectView rVF = new RedirectView("/address/{id}");
		    redirectAttrs.addFlashAttribute("mensaje", "ERROR: The chosen address is related to other objects.")
		    			.addFlashAttribute("clase", "danger");
			return rVF;
		} else {
			return rVT;
		}
	}
		
}
