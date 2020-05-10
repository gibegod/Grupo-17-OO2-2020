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
import com.controlstock.models.AddressModel;
import com.controlstock.models.StoreModel;
import com.controlstock.services.IStoreService;

@Controller
@RequestMapping("/store")
public class StoreController {

	@Autowired
	@Qualifier("storeService")
	private IStoreService storeService;

	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STORE_INDEX);
		mAV.addObject("stores", storeService.getAll());
		return mAV;
	}

	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STORE_NEW);
		mAV.addObject("store", new StoreModel());
		return mAV;
	}

	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("store") StoreModel storeModel, @ModelAttribute("address") AddressModel addressModel) {
		System.out.println(addressModel.getLatitude());
		System.out.println(addressModel.getLatitude());
		System.out.println(addressModel.getLatitude());
		System.out.println(addressModel.getLatitude());
		System.out.println(addressModel.getLatitude());
		System.out.println(addressModel.getLatitude());
		storeService.insertOrUpdate(storeModel);
		return new RedirectView(ViewRouteHelper.STORE_ROOT);
	}

	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.STORE_UPDATE);
		mAV.addObject("store", storeService.findById(id));
		return mAV;
	}

	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("store") StoreModel storeModel) {
		storeService.insertOrUpdate(storeModel);
		return new RedirectView(ViewRouteHelper.STORE_ROOT);
	}

	@PostMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		storeService.remove(id);
		return new RedirectView(ViewRouteHelper.STORE_ROOT);
	}

}
